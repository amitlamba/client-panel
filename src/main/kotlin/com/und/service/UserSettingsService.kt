package com.und.service

import com.und.common.utils.DateUtils
import com.und.model.Status
import com.und.model.jpa.ServiceProviderCredentials
import com.und.repository.ServiceProviderCredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserSettingsService {

    @Autowired
    lateinit private var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository

    private val emailServiceProvider = "Email Service Provider"
    private val smsServiceProvider = "Sms Service Provider"

    fun getEmailServiceProvider(clientID: Long): List<ServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, emailServiceProvider)
    }

    fun getEmailServiceProvider(clientID: Long, id: Long): ServiceProviderCredentials? {
        return serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, emailServiceProvider)
    }

    fun saveEmailServiceProvider(serviceProviderCredentials: ServiceProviderCredentials): Long? {
        serviceProviderCredentials.status = Status.ACTIVE
        serviceProviderCredentials.dateModified = DateUtils().now()
        if (serviceProviderCredentials.id == null)
            serviceProviderCredentials.dateCreated = DateUtils().now() //FIXME: Date Created should be for the first time only
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }

    fun getSmsServiceProvider(clientID: Long): List<ServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, smsServiceProvider)
    }

    fun getSmsServiceProvider(clientID: Long, id: Long): ServiceProviderCredentials? {
        return serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, smsServiceProvider)
    }

    fun saveSmsServiceProvider(serviceProviderCredentials: ServiceProviderCredentials): Long? {
        serviceProviderCredentials.status = Status.ACTIVE
        serviceProviderCredentials.dateModified = DateUtils().now()
        if (serviceProviderCredentials.id == null)
            serviceProviderCredentials.dateCreated = DateUtils().now() //FIXME: Date Created should be for the first time only
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }

    fun getServiceProviders(clientID: Long): List<ServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientID(clientID)
    }
}