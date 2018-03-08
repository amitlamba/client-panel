package com.und.web.controller

import com.und.model.jpa.ServiceProviderCredentials
import com.und.model.jpa.ServiceProviderType
import com.und.security.utils.AuthenticationUtils
import com.und.service.UserSettingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/setting")
class UserSettingsController {

    @Autowired
    private lateinit var userSettingsService: UserSettingsService

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
        serviceProviderCredentials.serviceProviderType = ServiceProviderType.EMAIL_SERVICE_PROVIDER
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
        serviceProviderCredentials.serviceProviderType = ServiceProviderType.SMS_SERVICE_PROVIDER
        return userSettingsService.saveSmsServiceProvider(serviceProviderCredentials)
    }
}