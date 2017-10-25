package com.und.security.service

import com.und.repository.ClientRepository
import com.und.security.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var jwtKeyService: JWTKeyService

    fun save(client: Client): Client {
        val client = clientRepository.save(client)
        return client
    }

    fun findByEmail(email: String): Client? {
        return clientRepository.findByEmail(email)
    }
}