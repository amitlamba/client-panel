package com.und.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.und.common.utils.DateUtils
import com.und.model.Status
import com.und.model.jpa.ServiceProviderCredentials
import com.und.web.model.ServiceProviderCredentials as WebServiceProviderCredentials
import com.und.repository.ServiceProviderCredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserSettingsService {

    @Autowired
    lateinit private var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository

    private val emailServiceProvider = "Email Service Provider"
    private val smsServiceProvider = "Sms Service Provider"

    fun getEmailServiceProvider(clientID: Long): List<WebServiceProviderCredentials> {
        val spCredsList = serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, emailServiceProvider)
        val wspCreds = mutableListOf<WebServiceProviderCredentials>()
        spCredsList.forEach { wspCreds.add(buildWebServiceProviderCredentials(it)) }
        return wspCreds
    }

    fun getEmailServiceProvider(clientID: Long, id: Long): WebServiceProviderCredentials? {
        val spCreds = serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, emailServiceProvider)
        return buildWebServiceProviderCredentials(spCreds!!)
    }

    fun saveEmailServiceProvider(webServiceProviderCredentials: WebServiceProviderCredentials): Long? {
        webServiceProviderCredentials.status = Status.ACTIVE
        webServiceProviderCredentials.dateModified = DateUtils().now()
        if (webServiceProviderCredentials.id == null)
            webServiceProviderCredentials.dateCreated = DateUtils().now() //FIXME: Date Created should be for the first time only

        val serviceProviderCredentials = buildServiceProviderCredentials(webServiceProviderCredentials)
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }

    fun getSmsServiceProvider(clientID: Long): List<WebServiceProviderCredentials> {
        val spCredsList = serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, smsServiceProvider)
        val wspCreds = mutableListOf<WebServiceProviderCredentials>()
        spCredsList.forEach { wspCreds.add(buildWebServiceProviderCredentials(it)) }
        return wspCreds
    }

    fun getSmsServiceProvider(clientID: Long, id: Long): WebServiceProviderCredentials? {
        val spCreds = serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, smsServiceProvider)
        return buildWebServiceProviderCredentials(spCreds!!)
    }

    fun saveSmsServiceProvider(webServiceProviderCredentials: WebServiceProviderCredentials): Long? {
        webServiceProviderCredentials.status = Status.ACTIVE
        webServiceProviderCredentials.dateModified = DateUtils().now()
        if (webServiceProviderCredentials.id == null)
            webServiceProviderCredentials.dateCreated = DateUtils().now() //FIXME: Date Created should be for the first time only

        val serviceProviderCredentials = buildServiceProviderCredentials(webServiceProviderCredentials)
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }

    fun getServiceProviders(clientID: Long): List<WebServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientID(clientID).map { data -> buildWebServiceProviderCredentials(data) }
    }

    fun buildServiceProviderCredentials(webServiceProviderCredentials: WebServiceProviderCredentials): ServiceProviderCredentials {
        val spCreds = ServiceProviderCredentials()
        with(spCreds) {
            spCreds.appuserID = webServiceProviderCredentials.appuserID
            spCreds.clientID = webServiceProviderCredentials.clientID
            spCreds.dateCreated = webServiceProviderCredentials.dateCreated
            spCreds.dateModified = webServiceProviderCredentials.dateModified
            spCreds.id = webServiceProviderCredentials.id
            spCreds.serviceProvider = webServiceProviderCredentials.serviceProvider
            spCreds.serviceProviderType = webServiceProviderCredentials.serviceProviderType
            spCreds.status = webServiceProviderCredentials.status
            spCreds.credentialsMap = GsonBuilder().create().toJson(webServiceProviderCredentials.credentialsMap)
        }
        return spCreds
    }

    fun buildWebServiceProviderCredentials(serviceProviderCredentials: ServiceProviderCredentials): WebServiceProviderCredentials {
        val wspCreds = WebServiceProviderCredentials()
        with(wspCreds) {
            wspCreds.appuserID = serviceProviderCredentials.appuserID
            wspCreds.clientID = serviceProviderCredentials.clientID
            wspCreds.dateCreated = serviceProviderCredentials.dateCreated
            wspCreds.dateModified = serviceProviderCredentials.dateModified
            wspCreds.id = serviceProviderCredentials.id
            wspCreds.serviceProvider = serviceProviderCredentials.serviceProvider
            wspCreds.serviceProviderType = serviceProviderCredentials.serviceProviderType
            wspCreds.status = serviceProviderCredentials.status
            wspCreds.credentialsMap = Gson().fromJson<HashMap<String, String>>(serviceProviderCredentials.credentialsMap, HashMap<String, String>().javaClass)
        }
        return wspCreds
    }
}