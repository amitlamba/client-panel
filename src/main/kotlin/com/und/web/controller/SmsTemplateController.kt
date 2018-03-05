package com.und.web.controller

import com.und.common.utils.loggerFor
import com.und.model.jpa.SmsTemplate
import com.und.security.utils.AuthenticationUtils
import com.und.service.SmsTemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/client/sms")
class SmsTemplateController {
    companion object {

        protected val logger = loggerFor(SmsTemplateController::class.java)
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
    fun saveSmsTemplate(@RequestBody smsTemplate: SmsTemplate): Long {
        smsTemplate.clientID = AuthenticationUtils.clientID
        smsTemplate.appuserID=AuthenticationUtils.principal.id
        return smsTemplateService.saveSmsTemplate(smsTemplate)
    }

    @RequestMapping(value = "/user-event-attributes", method = arrayOf(RequestMethod.GET))
    fun getUserEventAttributes() {
        smsTemplateService.getUserEventAttributes()
    }
}