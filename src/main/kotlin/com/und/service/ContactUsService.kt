package com.und.service

import com.und.common.utils.loggerFor
import com.und.model.jpa.ContactUs
import com.und.repository.ContactUsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.und.web.model.ContactUs as WebContactUs

@Service
class ContactUsService {

    companion object {

        protected val logger = loggerFor(ContactUsService::class.java)
    }

    @Autowired
    private lateinit var contactUsRepository: ContactUsRepository

    fun save(webContactUs: WebContactUs) {

        val contactUs=buildContactUs(webContactUs)
        val persistedContactUs=contactUsRepository.save(contactUs)
        webContactUs.id=persistedContactUs.id

    }


    fun buildContactUs(webContactUs: WebContactUs):ContactUs{

        val contactUS=ContactUs()
        contactUS.name= webContactUs.name!!
        contactUS.email= webContactUs.email!!
        contactUS.mobileNo= webContactUs.mobileNo!!
        contactUS.message= webContactUs.message!!

        return contactUS
    }

}