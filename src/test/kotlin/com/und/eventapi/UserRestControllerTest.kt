package com.und.eventapi

import com.und.security.RestTokenUtil
import com.und.security.RestUserFactory
import com.und.security.model.Authority
import com.und.security.model.AuthorityName
import com.und.security.model.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRestControllerTest {

    private var mvc: MockMvc? = null

    @Autowired
    lateinit private var context: WebApplicationContext

    @MockBean
    lateinit private var restTokenUtil: RestTokenUtil

    @MockBean
    lateinit private var userDetailsService: UserDetailsService

    @Before
    fun setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetUnauthorizedWithoutRole() {

        this.mvc!!.perform(get("/user"))
                .andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser(roles = arrayOf("USER"))
    @Throws(Exception::class)
    fun getPersonsSuccessfullyWithUserRole() {

        val authority = Authority()
        authority.id = 1L
        authority.name = AuthorityName.ROLE_ADMIN
        val authorities = Arrays.asList(authority)

        val user = User()
        user.username = "username"
        user.authorities = authorities
        user.enabled = java.lang.Boolean.TRUE
        user.clientSecret = "secret"
        user.lastPasswordResetDate = Date(System.currentTimeMillis() + 1000 * 1000)

        val eventUser = RestUserFactory.create(user)

        `when`<String>(this.restTokenUtil.getUsernameFromToken(any(), any())).thenReturn(user.username)

        `when`<UserDetails>(this.userDetailsService.loadUserByUsername(any())).thenReturn(eventUser)

        this.mvc!!.perform(get("/user"))
                .andExpect(status().is2xxSuccessful)
    }

}

