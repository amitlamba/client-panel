package com.und.repository

import com.und.security.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientVerificationRepository : JpaRepository<Client, Long> {
    fun findByName(name: String): Client?
}