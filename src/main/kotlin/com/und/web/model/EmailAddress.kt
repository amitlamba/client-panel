package com.und.web.model

import javax.mail.internet.InternetAddress
import javax.validation.constraints.Email

data class EmailAddress(@Email val address: String, val personal: String) {
    companion object {
        fun fromInternetAddress(internetAddress: InternetAddress): EmailAddress {
            return EmailAddress(internetAddress.address, internetAddress.personal)
        }
        fun toInternetAddress(emailAddress: EmailAddress): InternetAddress {
            return InternetAddress(emailAddress.address, emailAddress.personal)
        }
    }
}