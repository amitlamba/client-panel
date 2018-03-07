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

    @GetMapping(value = ["/default-templates"])
    fun getDefaultSmsTemplates(): List<SmsTemplate> {
        logger.debug("Inside getDefaultSmsTemplates method")
        return smsTemplateService.getDefaultSmsTemplates()
    }

    @GetMapping(value = ["/templates"])
    fun getClientSmsTemplates(@RequestParam(value = "id", required = false) id: Long? = null): List<SmsTemplate> {
        return smsTemplateService.getClientSmsTemplates(AuthenticationUtils.clientID!!, id)
    }

    @PostMapping(value = ["/save-template"])
    fun saveSmsTemplate(@RequestBody smsTemplate: SmsTemplate): Long {
        smsTemplate.clientID = AuthenticationUtils.clientID
        smsTemplate.appuserID=AuthenticationUtils.principal.id
        return smsTemplateService.saveSmsTemplate(smsTemplate)
    }

    @GetMapping(value = ["/user-event-attributes"])
    fun getUserEventAttributes() {
        smsTemplateService.getUserEventAttributes()
    }
}