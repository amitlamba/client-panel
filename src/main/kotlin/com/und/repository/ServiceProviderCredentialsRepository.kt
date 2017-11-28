package com.und.repository

import com.und.model.ServiceProvider
import com.und.model.ServiceProviderCredentials
import com.und.model.ServiceProviderType
import com.und.model.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceProviderCredentialsRepository : JpaRepository<ServiceProviderCredentials, Long> {
    fun findAllByClientIDAndServiceProviderAndStatus(clientID: Long, serviceProviderType: ServiceProviderType, status: Status): List<ServiceProviderCredentials>
    fun findAllByClientIDAndServiceProvider(clientID: Long, serviceProviderType: ServiceProviderType): List<ServiceProviderCredentials>
    fun findAllByClientIDAndIdAndServiceProvider(clientID: Long, id: Long, serviceProviderType: ServiceProviderType): ServiceProviderCredentials?
}