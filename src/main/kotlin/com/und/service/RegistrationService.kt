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


    fun register(registrationRequest: RegistrationRequest): Client {
        //TODO change response type to wrap errors and success
        val client = buildClient(registrationRequest)
        val user = buildUser(registrationRequest)
        client.addUser(user)
        //TODO add one more user of type event

        return clientRepository.save(client)
        //TODO send activation email

    }

    private fun buildUser(registrationRequest: RegistrationRequest): User {
        val user: User = User()
        with(user) {
            email = registrationRequest.email
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
        return user
    }

    private fun buildClient(registrationRequest: RegistrationRequest): Client {
        val client: Client = Client()
        with(client) {
            email = registrationRequest.email
            phone = registrationRequest.phone
            name = registrationRequest.name
            //TODO add state
        }
        return client
    }

}