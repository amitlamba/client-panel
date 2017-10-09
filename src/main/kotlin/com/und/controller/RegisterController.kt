package com.und.controller

import com.und.common.utils.loggerFor
import com.und.model.RegistrationRequest
import com.und.security.model.User
import com.und.security.repository.UserRepository
import com.und.service.RegistrationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class RegisterController {

    companion object {

        protected val logger = loggerFor(javaClass)
    }

    @Autowired
    lateinit var registrationService: RegistrationService

    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.GET))
    fun registerForm() {

    }

    @RequestMapping(value = "/register", method = arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody request: RegistrationRequest) {
        registrationService.validate(request)
        registrationService.register(request)

    }

    @RequestMapping(value = "/verifyemail/{code}", method = arrayOf(RequestMethod.GET))
    fun verifyEmail(@PathVariable code: String) {
        registrationService.verifyEmail(code)

    }

    @RequestMapping(value = "/newverifyemail/{email}", method = arrayOf(RequestMethod.GET))
    fun newverifyEmail(@PathVariable email: String) {
        registrationService.sendVerificationEmail(email)

    }
}