package com.und.service

import com.und.common.utils.loggerFor
import com.und.model.Email
import com.und.model.EmailRead
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class EmailService {

    @Autowired
    lateinit private var kafkaTemplate: KafkaTemplate<String, Email>

    @Autowired
    private lateinit var kafkaTemplateEmailRead: KafkaTemplate<String, EmailRead>

    @Value(value = "\${kafka.topic.email}")
    private var topic: String = "Email"

    private var emailReadTopic: String = "EmailRead"

    companion object {

        protected val logger = loggerFor(EmailService::class.java)
    }

    fun toKafka(email: Email): Email {

        val future = kafkaTemplate.send(topic, email.clientID.toString(), email)
        future.addCallback(object : ListenableFutureCallback<SendResult<String, Email>> {
            override fun onSuccess(result: SendResult<String, Email>) {
                logger.debug("Sent message: " + result)
            }

            override fun onFailure(ex: Throwable) {
                logger.error("Failed to send message", ex.message)
            }
        })
        return email
    }

    fun trackEmailRead(emailRead: EmailRead): EmailRead {
        val future = kafkaTemplateEmailRead.send(emailReadTopic, emailRead.clientID.toString(), emailRead)
        future.addCallback(object : ListenableFutureCallback<SendResult<String, EmailRead>> {
            override fun onSuccess(result: SendResult<String, EmailRead>) {
                logger.debug("Sent message: " + result)
            }

            override fun onFailure(ex: Throwable) {
                logger.error("Failed to send message", ex.message)
            }
        })
        return emailRead
    }
}