package com.und.security.controller

import com.und.security.model.RestAuthenticationRequest
import com.und.security.model.UndUserDetails
import com.und.security.service.SecurityAuthenticationResponse
import com.und.security.utils.RestTokenUtil
import com.und.security.service.JWTKeyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.mobile.device.Device
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationRestController {


    @Autowired
    lateinit private var authenticationManager: AuthenticationManager

    @Autowired
    lateinit private var restTokenUtil: RestTokenUtil

    @Autowired
    lateinit private var userDetailsService: UserDetailsService

    @Autowired
    lateinit private var jwtKeyService: JWTKeyService

    @RequestMapping(value = "\${security.route.authentication.path}", method = arrayOf(RequestMethod.POST))
    @Throws(AuthenticationException::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: RestAuthenticationRequest, device: Device): ResponseEntity<*> {

        // Perform the security
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        authenticationRequest.username,
                        authenticationRequest.password
                )
        )
        SecurityContextHolder.getContext().authentication = authentication

        // Reload password post-security so we can generate token
        val user = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = if (user != null && user is UndUserDetails) {
            jwtKeyService.generateJwt(user, device)
        } else ""

        // Return the token
        return ResponseEntity.ok(SecurityAuthenticationResponse(token))
    }


}
