package com.und.web.controller

import com.und.security.utils.AuthenticationUtils
import com.und.service.UserSettingsService
import com.und.web.model.AccountSettings
import com.und.web.model.EmailAddress
import com.und.web.model.ServiceProviderCredentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/setting")
class UserSettingsController {

    @Autowired
    private lateinit var userSettingsService: UserSettingsService

    @GetMapping(value = ["/service-providers"])
    fun getServiceProviders(): List<ServiceProviderCredentials> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getServiceProviders(clientID!!)
    }

    @GetMapping(value = ["/email-service-providers"])
    fun getEmailServiceProviders(): List<ServiceProviderCredentials> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getEmailServiceProvider(clientID!!)
    }

    @GetMapping(value = ["/email-service-provider/{id}"])
    fun getEmailServiceProvider(@PathVariable id: Long): ServiceProviderCredentials? {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getEmailServiceProvider(clientID!!, id)
    }

    @PostMapping(value = ["/email-service-provider/save"])
    fun saveEmailServiceProvider(@RequestBody serviceProviderCredentials: ServiceProviderCredentials): Long? {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        serviceProviderCredentials.appuserID = userID
        serviceProviderCredentials.clientID = clientID
        serviceProviderCredentials.serviceProviderType = "Email Service Provider"
        return userSettingsService.saveEmailServiceProvider(serviceProviderCredentials)
    }

    @GetMapping(value = ["/sms-service-providers"])
    fun getSmsServiceProviders(): List<ServiceProviderCredentials> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getSmsServiceProvider(clientID!!)
    }

    @GetMapping(value = ["/sms-service-provider/{id}"])
    fun getSmsServiceProvider(@PathVariable id: Long): ServiceProviderCredentials? {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getSmsServiceProvider(clientID!!, id)
    }

    @PostMapping(value = ["/sms-service-provider/save"])
    fun saveSmsServiceProvider(@RequestBody serviceProviderCredentials: ServiceProviderCredentials): Long? {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        serviceProviderCredentials.appuserID = userID
        serviceProviderCredentials.clientID = clientID
        serviceProviderCredentials.serviceProviderType = "Sms Service Provider"
        return userSettingsService.saveSmsServiceProvider(serviceProviderCredentials)
    }

    @PostMapping(value = ["/senders-email/add"])
    fun addSendersEmail(@RequestBody email: EmailAddress) {
        val clientID = AuthenticationUtils.clientID
        userSettingsService.addSenderEmailAddress(email, clientID!!)
    }

    @GetMapping(value = ["/senders-email/list"])
    fun getSendersEmailList(): List<EmailAddress> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getSenderEmailAddresses(clientID!!)
    }

    @PostMapping(value = ["/senders-email/delete"])
    fun deleteSendersEmail(@RequestBody email: EmailAddress) {
        val clientID = AuthenticationUtils.clientID
        userSettingsService.removeSenderEmailAddress(email, clientID!!)
    }

    @PostMapping(value = ["/account-settings/save"])
    fun saveAccountSettings(@RequestBody accountSettings: AccountSettings) {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        return userSettingsService.saveAccountSettings(accountSettings, clientID, userID)
    }

    @GetMapping(value = ["/account-settings/get"])
    fun getAccountSettings(): AccountSettings {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        return userSettingsService.getAccountSettings(clientID, userID)
    }
}