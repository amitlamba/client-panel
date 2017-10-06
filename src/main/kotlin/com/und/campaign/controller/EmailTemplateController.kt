package com.und.campaign.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailTemplateController {

    @RequestMapping(value="/client/default-templates", method = arrayOf(RequestMethod.GET))
    fun getDefaultEmailTemplates() {

    }

    @RequestMapping(value="/client/email-templates", method = arrayOf(RequestMethod.GET))
    fun getClientEmailTemplates() {

    }

    @RequestMapping(value = "/client/save-email-template", method = arrayOf(RequestMethod.POST))
    fun saveEmailTemplate() {

    }

    @RequestMapping(value = "/client/user-event-attributes", method = arrayOf(RequestMethod.GET))
    fun getUserEventAttributes() {

    }
}