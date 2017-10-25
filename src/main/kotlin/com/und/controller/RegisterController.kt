package com.und.controller

import com.und.common.utils.loggerFor
import com.und.exception.UndBusinessValidationException
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
@RequestMapping("/register")
class RegisterController {

    companion object {

        protected val logger = loggerFor(RegisterController::class.java)
    }

    @Autowired
    lateinit var registrationService: RegistrationService

    @RequestMapping( method = arrayOf(RequestMethod.GET))
    fun registerForm() {

    }

    @RequestMapping( method = arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody request: RegistrationRequest) {
        val errors = registrationService.validate(request)
        if (errors.getFieldErrors().isNotEmpty()) {
            logger.error("business validation failure while registering ${request.email} , with errors ${errors.getFieldErrors()}")
            throw  UndBusinessValidationException(errors)
        }
        val client = registrationService.register(request)
        registrationService.sendVerificationEmail(client)
    }

    @RequestMapping(value = "/verifyemail/{email}/{code}", method = arrayOf(RequestMethod.GET))
    fun verifyEmail(@PathVariable email: String, @PathVariable code: String) {
        registrationService.verifyEmail(email,code)

    }

    @RequestMapping(value = "/sendvfnmail/{email}", method = arrayOf(RequestMethod.GET))
    fun newverifyEmail(@PathVariable email: String) {
        registrationService.sendReVerificationEmail(email)

    }

    @RequestMapping(value = "/forgotpassword/{email}", method = arrayOf(RequestMethod.GET))
    fun forgotpassword(@PathVariable email: String) {
        //TODO not implemented
    }

    @RequestMapping(value = "/resetpassword/{email}/{code}", method = arrayOf(RequestMethod.GET))
    fun resetpasswordForm(@PathVariable email: String) {
        //TODO not implemented
    }

    @RequestMapping(value = "/resetpassword/{email}/{code}", method = arrayOf(RequestMethod.POST))
    fun resetpassword(@PathVariable email: String,@PathVariable code: String, @RequestBody registrationRequest: RegistrationRequest) {
        //TODO not implemented
    }
}