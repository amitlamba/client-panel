package com.und.service

import com.und.common.utils.DateUtils
import com.und.common.utils.loggerFor
import com.und.exception.UndBusinessValidationException
import com.und.model.ClientVerification
import com.und.model.RegistrationRequest
import com.und.model.api.ValidationError
import com.und.repository.AuthorityRepository
import com.und.repository.ClientRepository
import com.und.security.model.AuthorityName
import com.und.security.model.Client
import com.und.security.model.EmailMessage
import com.und.security.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class RegistrationService {

    companion object {

        protected val logger = loggerFor(RegistrationService::class.java)
    }

    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var emailService: EmailService

    @Autowired
    lateinit var authorityRepository: AuthorityRepository

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
        client.clientVerification = buildClientVerificationEmail()
        return clientRepository.save(client)

    }

    private fun buildUser(registrationRequest: RegistrationRequest, userType: Int): User {
        val user = User()
        with(user) {
            email = registrationRequest.email
            password = passwordEncoder.encode(registrationRequest.password)
            firstname = registrationRequest.name
            lastname = registrationRequest.name

            mobile = registrationRequest.phone
            enabled = false
            lastPasswordResetDate = DateUtils().now()
            this.userType = userType
            when (userType) {
                1 -> {
                    username = "admin_${registrationRequest.email}"
                    val authority = authorityRepository.findByName(AuthorityName.ROLE_ADMIN)

                    if (authority != null) authorities = arrayListOf(authority)
                }
                2 -> {
                    username = "event_${registrationRequest.email}"
                    val authority = authorityRepository.findByName(AuthorityName.ROLE_EVENT)
                    if (authority != null) authorities = arrayListOf(authority)
                }
            }
            clientSecret = UUID.randomUUID().toString()

        }
        return user
    }

    private fun buildClient(registrationRequest: RegistrationRequest): Client {
        val client = Client()
        with(client) {
            email = registrationRequest.email
            phone = registrationRequest.phone
            name = registrationRequest.name
            //TODO add state
        }
        return client
    }

    private fun buildClientVerificationEmail(): ClientVerification {
        val clientVerification = ClientVerification()
        with(clientVerification) {
            emailCode = UUID.randomUUID().toString()
            //this.client = client
            this.emailCodeDate = DateUtils().now()
        }
        return clientVerification
    }

    fun verifyEmail(email: String, code: String) {
        val client = clientRepository.findByEmail(email)
        if (client != null) {
            val codeMatch = client.clientVerification.emailCode == code
            val expired = client.clientVerification.emailCodeDate.before(DateUtils().now())
            //FIXME convert exception to message wrapper
            when {
                codeMatch && !expired -> markAccountVerified(client)
                !codeMatch || expired -> {
                    val validationError = ValidationError()
                    validationError.addFieldError("emailVerification",
                            "Invalid Link, link has expired please request for new email")
                    throw UndBusinessValidationException(validationError)
                }

            }

        }

    }

    private fun markAccountVerified(client: Client) {
        client.emailVerified = true
        client.users.forEach { user -> user.enabled = true }
        clientRepository.save(client)
    }


    fun sendReVerificationEmail(email: String) {
        val client = clientByEmail(email)
        resetVerificationCode(client)
        sendVerificationEmail(client)
    }

    private fun resetVerificationCode(client: Client) {
        client.clientVerification = buildClientVerificationEmail()
        clientRepository.save(client)
    }

    private fun clientByEmail(email: String): Client {
        val client = clientRepository.findByEmail(email)
        if (client == null) {
            logger.error("No user registered with email $email")
            logger.error("email $email is already verified")
            val validationError = ValidationError()
            validationError.addFieldError("Email Verification",
                    "This email is not registered , please register first.")
            throw UndBusinessValidationException(validationError)
        }
        if (client.emailVerified) {
            logger.error("email $email is already verified")
            val validationError = ValidationError()
            validationError.addFieldError("Email Verification",
                    "This email has already been verified")
            throw UndBusinessValidationException(validationError)

        }
        return client
    }


    fun sendVerificationEmail(client: Client) {

        val message = EmailMessage(
                subject = "Welcome To UND !!!",
                from = "",
                to = "",
                body = emailService.buildMesageBody(client)

        )
        emailService.sendEmail(message)
        logger.debug("email sent succesfully")

    }


}