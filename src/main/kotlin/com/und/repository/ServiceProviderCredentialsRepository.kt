package com.und.repository

import com.und.model.jpa.ServiceProviderCredentials
import com.und.model.jpa.ServiceProviderType
import com.und.model.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceProviderCredentialsRepository : JpaRepository<ServiceProviderCredentials, Long> {
    fun findAllByClientIDAndServiceProviderTypeAndStatus(clientID: Long, serviceProviderType: ServiceProviderType, status: Status): List<ServiceProviderCredentials>
    fun findAllByClientIDAndServiceProviderType(clientID: Long, serviceProviderType: ServiceProviderType): List<ServiceProviderCredentials>
    fun findAllByClientIDAndIdAndServiceProviderType(clientID: Long, id: Long, serviceProviderType: ServiceProviderType): ServiceProviderCredentials?
}