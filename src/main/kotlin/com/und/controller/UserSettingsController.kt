package com.und.controller

import com.und.model.ServiceProviderCredentials
import com.und.model.ServiceProviderType
import com.und.security.utils.AuthenticationUtils
import com.und.service.UserSettingsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/setting")
class UserSettingsController {

    @Autowired
    lateinit private var userSettingsService: UserSettingsService

    @RequestMapping(value = "/email-service-providers", method = arrayOf(RequestMethod.GET))
    fun getEmailServiceProviders(): List<ServiceProviderCredentials> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getEmailServiceProvider(clientID!!)
    }

    @RequestMapping(value = "/email-service-provider/{id}", method = arrayOf(RequestMethod.GET))
    fun getEmailServiceProvider(@PathVariable id: Long): ServiceProviderCredentials? {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getEmailServiceProvider(clientID!!, id)
    }

    @RequestMapping(value = "/email-service-provider/save", method = arrayOf(RequestMethod.POST))
    fun saveEmailServiceProvider(@RequestParam serviceProviderCredentials: ServiceProviderCredentials): Long? {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        serviceProviderCredentials.appuserID=userID
        serviceProviderCredentials.clientID=clientID
        serviceProviderCredentials.serviceProviderType=ServiceProviderType.EMAIL_SERVICE_PROVIDER
        return userSettingsService.saveEmailServiceProvider(serviceProviderCredentials)
    }

    @RequestMapping(value = "/sms-service-providers", method = arrayOf(RequestMethod.GET))
    fun getSmsServiceProviders(): List<ServiceProviderCredentials> {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getSmsServiceProvider(clientID!!)
    }

    @RequestMapping(value = "/sms-service-provider/{id}", method = arrayOf(RequestMethod.GET))
    fun getSmsServiceProvider(@PathVariable id: Long): ServiceProviderCredentials? {
        val clientID = AuthenticationUtils.clientID
        return userSettingsService.getSmsServiceProvider(clientID!!, id)
    }

    @RequestMapping(value = "/sms-service-provider/save", method = arrayOf(RequestMethod.POST))
    fun saveSmsServiceProvider(@RequestParam serviceProviderCredentials: ServiceProviderCredentials): Long? {
        val clientID = AuthenticationUtils.clientID
        val userID = AuthenticationUtils.principal.id
        serviceProviderCredentials.appuserID=userID
        serviceProviderCredentials.clientID=clientID
        serviceProviderCredentials.serviceProviderType=ServiceProviderType.SMS_SERVICE_PROVIDER
        return userSettingsService.saveSmsServiceProvider(serviceProviderCredentials)
    }
}