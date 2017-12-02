package com.und.service

import com.und.model.SmsTemplate
import com.und.repository.SmsTemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SmsTemplateService {

    @Autowired
    lateinit private var smsTemplateRepository: SmsTemplateRepository

    fun getDefaultSmsTemplates(): List<SmsTemplate> {
        return smsTemplateRepository.findByClientIDAndStatus()
    }

    fun getClientSmsTemplates(clientID: Long, smsTemplateID: Long?): List<SmsTemplate> {
        if (smsTemplateID == null)
            return smsTemplateRepository.findByClientIDAndStatus(clientID)
        else
            return listOf(smsTemplateRepository.findByIdAndClientIDAndStatus(smsTemplateID, clientID))
    }

    fun saveSmsTemplate(smsTemplate: SmsTemplate): Long {
        val save = smsTemplateRepository.save(smsTemplate)
        return save.id!!
    }

    fun getUserEventAttributes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}