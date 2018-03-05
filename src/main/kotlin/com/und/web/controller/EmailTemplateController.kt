package com.und.web.controller

import com.und.common.utils.loggerFor
import com.und.model.jpa.EmailTemplate
import com.und.security.utils.AuthenticationUtils
import com.und.service.EmailTemplateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/client/email")
class EmailTemplateController {

    companion object {

        protected val logger = loggerFor(EmailTemplateController::class.java)
    }

    @Autowired
    private lateinit var emailTemplateService: EmailTemplateService

    @RequestMapping(value = "/default-templates", method = arrayOf(RequestMethod.GET))
    fun getDefaultEmailTemplates(): List<EmailTemplate> {
        logger.debug("Inside getDefaultEmailTemplates method")
        return emailTemplateService.getDefaultEmailTemplates()
    }

    @RequestMapping(value = "/templates", method = arrayOf(RequestMethod.GET))
    fun getClientEmailTemplates(@RequestParam(value = "id", required = false) id: Long? = null): List<EmailTemplate> {
        return emailTemplateService.getClientEmailTemplates(AuthenticationUtils.clientID!!, id)//TODO - Insert a valid clientID
    }

    @RequestMapping(value = "/save-template", method = arrayOf(RequestMethod.POST))
    fun saveEmailTemplate(@RequestBody emailTemplate: EmailTemplate): Long {
        emailTemplate.clientID=AuthenticationUtils.clientID
        emailTemplate.appuserID=AuthenticationUtils.principal.id
        return emailTemplateService.saveEmailTemplate(emailTemplate)
    }

    @RequestMapping(value = "/user-event-attributes", method = arrayOf(RequestMethod.GET))
    fun getUserEventAttributes() {
        emailTemplateService.getUserEventAttributes()
    }
}