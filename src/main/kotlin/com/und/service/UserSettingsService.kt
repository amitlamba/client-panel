package com.und.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.und.model.Status
import com.und.model.jpa.ClientSettings
import com.und.model.jpa.ServiceProviderCredentials
import com.und.repository.ClientSettingsRepository
import com.und.web.model.ServiceProviderCredentials as WebServiceProviderCredentials
import com.und.repository.ServiceProviderCredentialsRepository
import com.und.web.model.AccountSettings
import com.und.web.model.EmailAddress
import com.und.web.model.UnSubscribeLink

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.PostConstruct
import com.fasterxml.jackson.module.kotlin.*

@Service
class UserSettingsService {

    @Autowired
    private lateinit var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository

    @Autowired
    private lateinit var clientSettingsRepository: ClientSettingsRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private var emptyArrayJson: String = "[]"


    private val emailServiceProvider = "Email Service Provider"
    private val smsServiceProvider = "Sms Service Provider"

    @PostConstruct
    fun setUp() {
        emptyArrayJson = objectMapper.writeValueAsString(emptyArray<String>())//"[]"//objectMapper.
    }

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
            spCreds.credentialsMap = objectMapper.writeValueAsString(webServiceProviderCredentials.credentialsMap)
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
            wspCreds.credentialsMap = objectMapper.readValue(serviceProviderCredentials.credentialsMap)
        }
        return wspCreds
    }

    fun saveAccountSettings(accountSettings: AccountSettings, clientID: Long?, userID: Long?) {
        //FIXME: Validate Timezone and Email Addresses
        val clientSettings = ClientSettings()
        clientSettings.id = accountSettings.id
        clientSettings.clientID = clientID
        clientSettings.authorizedUrls = objectMapper.writeValueAsString(accountSettings.urls)
        clientSettings.timezone = accountSettings.timezone
        clientSettingsRepository.save(clientSettings)
    }

    fun getAccountSettings(clientID: Long): Optional<AccountSettings> {
        val clientSettings = clientSettingsRepository.findByClientID(clientID)

        return if (clientSettings != null) {
            val setting =
                    AccountSettings(clientSettings.id, objectMapper.readValue(clientSettings.authorizedUrls
                            ?: emptyArrayJson), clientSettings.timezone)
            Optional.of(setting)
        } else Optional.empty()
    }

    @Transactional
    fun addSenderEmailAddress(emailAddress: EmailAddress, clientID: Long) {
        val emailAddresses = getSenderEmailAddresses(clientID)
        emailAddresses.add(emailAddress)
        clientSettingsRepository.saveSenderEmailAddresses(objectMapper.writeValueAsString(emailAddresses), clientID)
    }

    @Transactional
    fun removeSenderEmailAddress(emailAddress: EmailAddress, clientID: Long) {
        val emailAddresses = getSenderEmailAddresses(clientID)
        emailAddresses.removeIf {
            emailAddress.address == it.address && emailAddress.personal == it.personal
        }
        clientSettingsRepository.saveSenderEmailAddresses(objectMapper.writeValueAsString(emailAddresses), clientID)
    }


    fun senderEmailAddresses(clientID: Long): ArrayList<EmailAddress> {
        return getSenderEmailAddresses(clientID)
    }


    fun saveUnSubscribeLink(request: UnSubscribeLink, clientID: Long?) {

        val clientSettings = clientSettingsRepository.findByClientID(clientID!!)
        if (clientSettings == null) {

            val clientSettingsNew = ClientSettings()
            clientSettingsNew.unSubscribeLink = request.unSubscribeLink
            clientSettingsNew.clientID = clientID
            clientSettingsRepository.save(clientSettingsNew)

        } else {

            clientSettings.unSubscribeLink = request.unSubscribeLink
            clientSettingsRepository.save(clientSettings)

        }

    }

    fun getUnSubscribeLink(clientID: Long?): UnSubscribeLink {

        val clientSettings = clientSettingsRepository.findByClientID(clientID!!)
        val linkUrl = clientSettings?.unSubscribeLink

        return linkUrl?.let { link ->
            val unSubscribeLink = UnSubscribeLink()
            unSubscribeLink.unSubscribeLink = link
            return unSubscribeLink
        } ?: UnSubscribeLink()

    }

    private fun getSenderEmailAddresses(clientID: Long): ArrayList<EmailAddress> {
        val emailAddressesJson: String = clientSettingsRepository.findSenderEmailAddressesByClientId(clientID)
                ?: emptyArrayJson

        return objectMapper.readValue(emailAddressesJson)
    }

}