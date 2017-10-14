package com.und.controller

import com.und.common.utils.loggerFor
import com.und.model.EmailTemplate
import com.und.security.utils.AuthenticationUtils
import com.und.service.EmailTemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class EmailTemplateController {

    companion object {

        protected val logger = loggerFor(javaClass)
    }

    @Autowired
    private lateinit var emailTemplateService: EmailTemplateService

    @RequestMapping(value = "/client/default-templates", method = arrayOf(RequestMethod.GET))
    fun getDefaultEmailTemplates(): List<EmailTemplate> {
        logger.debug("Inside getDefaultEmailTemplates method")
        return emailTemplateService.getDefaultEmailTemplates()
    }

    @RequestMapping(value = "/client/email-templates", method = arrayOf(RequestMethod.GET))
    fun getClientEmailTemplates(): List<EmailTemplate> {
        return emailTemplateService.getClientEmailTemplates(AuthenticationUtils.clientID!!)//TODO - Insert a valid clientID
    }

    @RequestMapping(value = "/client/save-email-template", method = arrayOf(RequestMethod.POST))
    fun saveEmailTemplate(@RequestParam @Valid emailTemplate: EmailTemplate): Long {
        emailTemplate.clientID=AuthenticationUtils.clientID
        return emailTemplateService.saveEmailTemplate(emailTemplate)
    }

    @RequestMapping(value = "/client/user-event-attributes", method = arrayOf(RequestMethod.GET))
    fun getUserEventAttributes() {
        emailTemplateService.getUserEventAttributes()
    }
}