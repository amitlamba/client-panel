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


            if (token.key.equals(user.key)) {
                if (user.key!=null && restTokenUtil.validateToken(user.key, user)) {
                    //FIXME hack for preventing null
                    val password = if(user.password !=null) user.password.toString() else ""
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