package com.und.service

import com.und.common.utils.loggerFor
import com.und.model.ClientVerification
import com.und.security.model.Client
import com.und.security.model.EmailMessage
import com.und.security.model.User
import org.springframework.stereotype.Service

@Service
class EmailService {

    companion object {

        protected val logger = loggerFor(javaClass)
    }

    fun sendEmail(message: EmailMessage){
        //TODO implement send email through messaging
        logger.info("email being sent -------------")

        logger.info("from ${message.from}")
        logger.info("to ${message.to}")
        logger.info("subject ${message.subject}")
        logger.info("body ${message.body}")

        logger.info("email sent -------------")
    }


    fun buildMesageBody(user: User, emailCode: String): String {
        val url = "http://localhost:8080/verifyemail/${user.email}/${emailCode}"
        return """
                    Dear ${user.username},
                        Welcome your login id is admin_${user.username} and password is ${user.password}
                        To be able to use it you first need to verify email by clicking on below link
                        ${url}
                    Thanks
                    Team UND
               """.trimIndent()
    }
}