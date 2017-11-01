package com.und.security.filter

import com.und.security.utils.KEYTYPE
import com.und.security.utils.RestTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAuthenticationTokenFilter : OncePerRequestFilter() {

    //private val logger = LogFactory.getLog(this.javaClass)

    @Autowired
    lateinit private var undUserDetailsService: UserDetailsService

    @Autowired
    lateinit private var restTokenUtil: RestTokenUtil

    @Value("\${security.header.token}")
    lateinit private var tokenHeader: String


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authToken = request.getHeader(this.tokenHeader)
        //TODO validate token from redis here,
        // insert token while generating, if already there return old one
        // check If token exists in set, return failure if nor
        //if exists, get secret of that client's token, verify if fails return error,
        // else extract all claims and populate userdetails


        if (SecurityContextHolder.getContext().authentication == null && authToken != null) {
            logger.info("checking authentication for token $authToken ")

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            val (userDetails, jwtToken) = restTokenUtil.validateToken(authToken, KEYTYPE.LOGIN)
            if (userDetails!=null) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                logger.info("authenticated user usernameFromToken, setting security context")
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        chain.doFilter(request, response)
    }
}