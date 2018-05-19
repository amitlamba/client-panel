package com.und.web.controller

import com.und.service.ContactUsService
import com.und.web.model.ContactUs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/contactUs")
class ContactUsController {

    @Autowired
    lateinit var contactUsService: ContactUsService

    @PostMapping("/save")
    fun saveContactUsDetails(@Valid @RequestBody contactInfo: ContactUs): ResponseEntity<Unit> {
        contactUsService.save(contactInfo)
        return ResponseEntity(HttpStatus.OK)

    }


}