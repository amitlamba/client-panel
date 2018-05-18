package com.und.web.controller

import com.und.web.model.ContactUs
import com.und.service.ContactUsService
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var contactUsService: ContactUsService

    @PostMapping("/save")
    fun saveContactUsDetails(@Valid @RequestBody request: ContactUs, response: HttpServletResponse) {

        if (request.name != null && request.email != null && request.message != null && request.mobileNo != null) {

            contactUsService.save(request)

            //TODO Send Email ar response
            response.status=HttpServletResponse.SC_OK
        }
        else
            response.status=HttpServletResponse.SC_NOT_ACCEPTABLE
    }


}