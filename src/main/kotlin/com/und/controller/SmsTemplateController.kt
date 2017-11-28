package com.und.controller

import com.und.common.utils.loggerFor
import com.und.model.SmsTemplate
import com.und.security.utils.AuthenticationUtils
import com.und.service.SmsTemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/client/sms")
class SmsTemplateController {
    companion object {

        protected val logger = loggerFor(javaClass)
    }

    @Autowired
    private lateinit var smsTemplateService: SmsTemplateService

    @RequestMapping(value = "/default-templates", method = arrayOf(RequestMethod.GET))
    fun getDefaultSmsTemplates(): List<SmsTemplate> {
        logger.debug("Inside getDefaultSmsTemplates method")
        return smsTemplateService.getDefaultSmsTemplates()
    }

    @RequestMapping(value = "/templates", method = arrayOf(RequestMethod.GET))
    fun getClientSmsTemplates(@RequestParam(value = "id", required = false) id: Long? = null): List<SmsTemplate> {
        return smsTemplateService.getClientSmsTemplates(AuthenticationUtils.clientID!!, id)
    }

    @RequestMapping(value = "/save-template", method = arrayOf(RequestMethod.POST))
    fun saveSmsTemplate(@RequestParam @Valid smsTemplate: SmsTemplate): Long {
        smsTemplate.clientID = AuthenticationUtils.clientID
        return smsTemplateService.saveSmsTemplate(smsTemplate)
    }

    @RequestMapping(value = "/user-event-attributes", method = arrayOf(RequestMethod.GET))
    fun getUserEventAttributes() {
        smsTemplateService.getUserEventAttributes()
    }
}