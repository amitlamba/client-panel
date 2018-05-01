package com.und.web.controller

import com.und.web.model.ContactUs
import com.und.service.ContactUsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import javax.validation.Valid
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.web.bind.annotation.*
import javax.mail.internet.MimeMessage
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/contactUs")
class ContactUsController {

/*
    companion object {

        protected val logger = loggerFor(ContactUsController::class.java)
    }
*/

    @Autowired
    lateinit var contactUsService: ContactUsService

    @PostMapping("/save")
    fun saveContactUsDetails(@Valid @RequestBody request: ContactUs, response: HttpServletResponse) {

        if (request.name != "" && request.email != "" && request.message != "" && request.mobileNo != "") {
            contactUsService.save(request)
            //FIXME INTERNAL SERVER ERROR
            //sendEmail()
            response.status=HttpServletResponse.SC_OK
        }
        else
            response.status=HttpServletResponse.SC_NOT_ACCEPTABLE
    }

    private fun sendEmail() {
        val sender = JavaMailSenderImpl()
        sender.host = "mail.host.com"
        val message = sender.createMimeMessage()
        val helper = MimeMessageHelper(message)

        helper.setTo("lakshbhutani@gmail.com")
        helper.setText("Or bhai kya hal hai")
        helper.setSubject("ContactUs API")
        sender.send(message)

    }

}