package com.und.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.und.common.utils.DateUtils
import com.und.model.Status
import com.und.model.jpa.ClientSettings
import com.und.model.jpa.ServiceProviderCredentials
import com.und.repository.ClientSettingsRepository
import com.und.web.model.ServiceProviderCredentials as WebServiceProviderCredentials
import com.und.repository.ServiceProviderCredentialsRepository
import com.und.web.model.AccountSettings
import com.und.web.model.EmailAddress
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserSettingsService {

    @Autowired
    lateinit private var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository
    @Autowired
    lateinit private var clientSettingsRepository: ClientSettingsRepository

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
        webServiceProviderCredentials.dateModified = LocalDateTime.now()
        if (webServiceProviderCredentials.id == null)
            webServiceProviderCredentials.dateCreated = LocalDateTime.now() //FIXME: Date Created should be for the first time only

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
        webServiceProviderCredentials.dateModified = LocalDateTime.now()
        if (webServiceProviderCredentials.id == null)
            webServiceProviderCredentials.dateCreated = LocalDateTime.now() //FIXME: Date Created should be for the first time only

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

            wspCreds.id = serviceProviderCredentials.id
            wspCreds.serviceProvider = serviceProviderCredentials.serviceProvider
            wspCreds.serviceProviderType = serviceProviderCredentials.serviceProviderType
            wspCreds.status = serviceProviderCredentials.status
            wspCreds.credentialsMap = Gson().fromJson<HashMap<String, String>>(serviceProviderCredentials.credentialsMap, HashMap<String, String>().javaClass)
        }
        return wspCreds
    }

    fun saveAccountSettings(accountSettings: AccountSettings, clientID: Long?, userID: Long?) {
        //FIXME: Validate Timezone and Email Addresses
        var clientSettings = ClientSettings()
        clientSettings.id = accountSettings.id
        clientSettings.clientID = clientID
        clientSettings.authorizedUrls = GsonBuilder().create().toJson(accountSettings.urls)
        clientSettings.timezone = accountSettings.timezone
        clientSettingsRepository.save(clientSettings)
    }

    fun getAccountSettings(clientID: Long?, userID: Long?): AccountSettings {
        val clientSettings = clientSettingsRepository.findByClientID(clientID!!)
        val tokenType = object : TypeToken<Array<String>>() {}.type
        val accountSettings = AccountSettings(clientSettings.id, Gson().fromJson<Array<String>>(clientSettings.authorizedUrls, tokenType),clientSettings.timezone!!)
        return accountSettings
    }

    @Transactional
    fun addSenderEmailAddress(emailAddress: EmailAddress, clientID: Long) {
        var emailAddressesJson: String? = clientSettingsRepository.findSenderEmailAddressesByClientId(clientID)
        if (emailAddressesJson == null) emailAddressesJson = "[]"
        var emailAddresses: ArrayList<EmailAddress> = Gson().fromJson<ArrayList<EmailAddress>>(emailAddressesJson, ArrayList<EmailAddress>().javaClass)
        emailAddresses.add(emailAddress)
        clientSettingsRepository.saveSenderEmailAddresses(GsonBuilder().create().toJson(emailAddresses), clientID)
    }

    @Transactional
    fun removeSenderEmailAddress(emailAddress: EmailAddress, clientID: Long) {
        var emailAddressesJson: String? = clientSettingsRepository.findSenderEmailAddressesByClientId(clientID)
        if (emailAddressesJson == null) emailAddressesJson = "[]"
        val eaListType = object : TypeToken<List<EmailAddress>>() {}.type
        var emailAddresses: ArrayList<EmailAddress> = Gson().fromJson(emailAddressesJson, eaListType)
        emailAddresses.removeIf { emailAddress.address.equals(it.address) && emailAddress.personal.equals(it.personal) }
        clientSettingsRepository.saveSenderEmailAddresses(GsonBuilder().create().toJson(emailAddresses), clientID)
    }

    fun getSenderEmailAddresses(clientID: Long): List<EmailAddress> {
        var emailAddressesJson: String? = clientSettingsRepository.findSenderEmailAddressesByClientId(clientID)
        if (emailAddressesJson == null) emailAddressesJson = "[]"
        val eaListType = object : TypeToken<List<EmailAddress>>() {}.type
        var emailAddresses: ArrayList<EmailAddress> = Gson().fromJson(emailAddressesJson, eaListType)
        return emailAddresses
    }
}