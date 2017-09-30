package com.und.service

import com.und.model.RegistrationRequest
import com.und.security.model.User
import com.und.security.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegistrationService {


    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var userRepository : UserRepository



    fun validate(registrationRequest: RegistrationRequest) {

    }


    fun register(registrationRequest: RegistrationRequest):User  {
        val user: User = User()
        with(user) {
            email= registrationRequest.email
            //TODO generate random password if empty for security
            password=passwordEncoder.encode(registrationRequest.password)
            firstname= registrationRequest.name
            username= registrationRequest.email
        }
       return  userRepository.save(user)

    }

}