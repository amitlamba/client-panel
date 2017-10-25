package com.und.controller

import com.und.common.utils.loggerFor
import com.und.exception.UndBusinessValidationException
import com.und.model.RegistrationRequest
import com.und.service.RegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/setting")
class UserProfileController {

    companion object {

        protected val logger = loggerFor(UserProfileController::class.java)
    }

    @Autowired
    lateinit var userProfileService: RegistrationService

    @RequestMapping( method = arrayOf(RequestMethod.GET))
    fun userProfileForm() {

    }

    @RequestMapping(value = "/updateUserDetails", method = arrayOf(RequestMethod.POST))
    fun updateUserDetails(@Valid @RequestBody request: RegistrationRequest) {
        //TODO check if its own email is being changed this error should not come, also more details might come in registration request object
        val errors = userProfileService.validate(request)
        if (errors.getFieldErrors().isNotEmpty()) {
            logger.error("business validation failure while registering ${request.email} , with errors ${errors.getFieldErrors()}")
            throw  UndBusinessValidationException(errors)
        }
        val client = userProfileService.update(request)
        userProfileService.sendVerificationEmail(client)
    }



    @RequestMapping(value = "/generatetoken", method = arrayOf(RequestMethod.POST))
    fun generateToken(@PathVariable email: String, @PathVariable code: String, @RequestBody registrationRequest: RegistrationRequest) {
        //TODO not implemented
    }
}