package com.und.repository

import com.und.security.model.Client
import com.und.security.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
    fun findByName(name: String): Client?
}
