package com.und.service

import com.und.model.EmailTemplate
import com.und.repository.EmailTemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmailTemplateService {

    @Autowired
    lateinit private var emailTemplateRepository: EmailTemplateRepository

    fun getDefaultEmailTemplates(): List<EmailTemplate> {
        val emailTemplates = emailTemplateRepository.findByClientID()
        return emailTemplates
    }

    fun getClientEmailTemplates(clientId: Long, emailTemplateId: Long? = null): List<EmailTemplate> {
        var emailTemplates: List<EmailTemplate>
        if(emailTemplateId != null) {
            emailTemplates = listOf(emailTemplateRepository.findByIdAndClientID(emailTemplateId, clientId))
        } else {
            emailTemplates = emailTemplateRepository.findByClientID(clientId)
        }
        return emailTemplates
    }

    fun saveEmailTemplate(emailTemplate: EmailTemplate): Long {
        val save = emailTemplateRepository.save(emailTemplate)
        return save.id!!
    }

    fun getUserEventAttributes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}