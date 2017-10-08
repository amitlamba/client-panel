package com.und.service

import com.und.common.utils.DateUtils
import com.und.model.RegistrationRequest
import com.und.repository.ClientRepository
import com.und.security.model.Client
import com.und.security.model.User
import com.und.security.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class RegistrationService {


    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun validate(registrationRequest: RegistrationRequest) {
        //TODO validate password rules are followed
        // validate user is not already registered
        //validate unique constraints
    }


    fun register(registrationRequest: RegistrationRequest): User {
        val client: Client = Client()
        with(client) {
            email = registrationRequest.email
            phone = registrationRequest.phone
            name = registrationRequest.name
            //TODO add state
        }
        val clientPersisted = clientRepository.save(client)

        val user: User = User()
        with(user) {
            email = registrationRequest.email
            clientId = clientPersisted.id
            password = passwordEncoder.encode(registrationRequest.password)
            firstname = registrationRequest.name
            lastname = registrationRequest.name
            username = registrationRequest.email
            mobile = registrationRequest.phone
            enabled = false
            lastPasswordResetDate = DateUtils().now()
            //FIXME type assign properly and handle accordingly
            userType = 1
            clientSecret = UUID.randomUUID().toString()

        }
        return userRepository.save(user)

    }

}