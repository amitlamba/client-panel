package com.und.security.service

import com.und.security.model.RestAuthenticationToken
import com.und.security.model.UndUserDetails
import com.und.security.model.User
import com.und.security.repository.UserRepository
import com.und.security.utils.RestTokenUtil
import com.und.security.utils.RestUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import sun.security.util.Password

/**
 * Created by shiv on 14/08/17.
 */
@Component
class RestAuthenticationProvider : AuthenticationProvider {

    @Autowired
    lateinit private var userRepository: UserRepository

    @Autowired
    lateinit private var restTokenUtil: RestTokenUtil

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication as RestAuthenticationToken
        val user = RestUserFactory.create(userRepository.findByUsername(token.getName())!!)


        if (user.key != null) {
            val password = user.password
            if (token.key == user.key
                    && restTokenUtil.validateToken(user.key, user)
                    && password != null) {
                return RestAuthenticationToken(user, password, user.authorities, token.key)
            }
        }

        throw BadCredentialsException("The credentials are invalid")

    }

    override fun supports(authentication: Class<*>): Boolean {
        return RestAuthenticationToken::class.java == authentication
    }

    private fun isEmptyCredntials(user: UndUserDetails): Boolean {
        return user.key == null || user.password == null

    }

}