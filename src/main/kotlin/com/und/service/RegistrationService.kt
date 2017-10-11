package com.und.service

import com.und.common.utils.DateUtils
import com.und.model.RegistrationRequest
import com.und.model.validation.ValidationError
import com.und.repository.ClientRepository
import com.und.security.model.Authority
import com.und.security.model.AuthorityName
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

    /**
     * validation should be part of transaction different from commit as it will
     * acquire lock on all table, even after this check non unique may exist, if database constraint is not followed.
     */
    fun validate(registrationRequest: RegistrationRequest): ValidationError {
        val client = clientRepository.findByEmail(registrationRequest.email)
        val error = ValidationError()
        if (client != null) {
            error.addFieldError("email", "This email id is already registered")
        }
        return error
    }


    fun register(registrationRequest: RegistrationRequest): Client {
        val client = buildClient(registrationRequest)
        val adminUser = buildUser(registrationRequest, 1)
        val eventUser = buildUser(registrationRequest, 2)
        client.addUser(adminUser)
        client.addUser(eventUser)
        return clientRepository.save(client)

    }

    private fun buildUser(registrationRequest: RegistrationRequest, userType: Int): User {
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
            this.userType = userType
            //FIXME handle authorities for default types
            when(userType) {
                1 -> {authorities = arrayListOf()}
                2 -> {authorities = arrayListOf()}
            }
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

    fun verifyEmail(code: String) {
        //TODO
        //validate link exists
        //validate link has not expired, and not already used
        //update tables and verify client if everything is valid

    }

    fun sendVerificationEmail(email: String) {
        //TODO
        val url = UUID.randomUUID().toString()
        //generate random url
        //persist url in verification table
        //send email

    }

}