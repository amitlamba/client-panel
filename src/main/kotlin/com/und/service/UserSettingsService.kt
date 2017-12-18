package com.und.service

import com.und.common.utils.DateUtils
import com.und.model.ServiceProviderCredentials
import com.und.model.ServiceProviderType
import com.und.model.Status
import com.und.repository.ServiceProviderCredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserSettingsService {

    @Autowired
    lateinit private var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository

    fun getEmailServiceProvider(clientID: Long): List<ServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, ServiceProviderType.EMAIL_SERVICE_PROVIDER)
    }

    fun getEmailServiceProvider(clientID: Long, id: Long): ServiceProviderCredentials? {
        return serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, ServiceProviderType.EMAIL_SERVICE_PROVIDER)
    }

    fun saveEmailServiceProvider(serviceProviderCredentials: ServiceProviderCredentials): Long? {
        serviceProviderCredentials.status = Status.ACTIVE
        serviceProviderCredentials.dateModified = DateUtils().now()
        serviceProviderCredentials.dateCreated = DateUtils().now() //FIXME: Date Created should be for the first time only
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }

    fun getSmsServiceProvider(clientID: Long): List<ServiceProviderCredentials> {
        return serviceProviderCredentialsRepository.findAllByClientIDAndServiceProviderType(clientID, ServiceProviderType.SMS_SERVICE_PROVIDER)
    }

    fun getSmsServiceProvider(clientID: Long, id: Long): ServiceProviderCredentials? {
        return serviceProviderCredentialsRepository.findAllByClientIDAndIdAndServiceProviderType(clientID, id, ServiceProviderType.SMS_SERVICE_PROVIDER)
    }

    fun saveSmsServiceProvider(serviceProviderCredentials: ServiceProviderCredentials): Long? {
        val saved = serviceProviderCredentialsRepository.save(serviceProviderCredentials)
        return saved.id!!
    }
}