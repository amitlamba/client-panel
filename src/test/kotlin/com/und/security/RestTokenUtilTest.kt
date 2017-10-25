package com.und.security

import com.und.common.utils.DateUtils
import com.und.security.model.AuthorityName
import com.und.security.model.UndUserDetails
import com.und.security.utils.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.assertj.core.util.DateUtil
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.test.util.ReflectionTestUtils
import java.util.*

/**
 * Created by shiv on 21/07/17.
 */
class RestTokenUtilTest {

    @Mock
    lateinit private var dateUtilsMock: DateUtils

    @InjectMocks
    lateinit private var restTokenUtil: RestTokenUtil

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        ReflectionTestUtils.setField(restTokenUtil, "expiration", 3600L) // one hour
        //ReflectionTestUtils.setField(restTokenUtil, "secret", "mySecret");
    }

    @Test
    @Throws(Exception::class)
    fun testGenerateTokenGeneratesDifferentTokensForDifferentCreationDates() {
        `when`(dateUtilsMock.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now())

        val token = createToken()
        val laterToken = createToken()

        assertThat(token).isNotEqualTo(laterToken)
    }

    @Test
    @Throws(Exception::class)
    fun getUsernameFromToken() {
        `when`(dateUtilsMock.now()).thenReturn(DateUtil.now())

        val token = createToken()

        val claims = restTokenUtil.getClaimsFromToken(token)
        assertThat(claims.username).isEqualTo(TEST_USER)
    }


    @Test
    @Throws(Exception::class)
    fun getCreatedDateFromToken() {
        val now = DateUtil.now()
        `when`(dateUtilsMock.now()).thenReturn(now)

        val token = createToken()
        val claims = restTokenUtil.getClaimsFromToken(token)
        assertThat(claims.creationDate).hasSameTimeAs(now)
    }

    @Test
    @Throws(Exception::class)
    fun getExpirationDateFromToken() {
        val now = DateUtil.now()
        `when`(dateUtilsMock.now()).thenReturn(now)
        val token = createToken()
        val claims = restTokenUtil.getClaimsFromToken(token)
        val expirationDateFromToken = claims.expirationDate
        assertThat(DateUtil.timeDifference(expirationDateFromToken, now)).isCloseTo(3600000L, within(1000L))
    }

    @Test
    @Throws(Exception::class)
    fun getAudienceFromToken() {
        `when`(dateUtilsMock.now()).thenReturn(DateUtil.now())
        val token = createToken()
        val claims = restTokenUtil.getClaimsFromToken(token)
        assertThat(claims.claimedAudience).isEqualTo(RestTokenUtil.AUDIENCE_WEB)
    }

    @Test
    @Throws(Exception::class)
    fun getRolesFromToken() {
        `when`(dateUtilsMock.now()).thenReturn(DateUtil.now())
        val token = createToken()
        val claims = restTokenUtil.getClaimsFromToken(token)

        assertThat(claims.roles).isEqualTo(
                arrayListOf(AuthorityName.ROLE_ADMIN.name, AuthorityName.ROLE_EVENT.name)
        )
    }

    // TODO write tests
    //
    //    @Test
    //    public void validateToken() throws Exception {
    //    }

    private fun createClaims(creationDate: String): Map<String, Any> {
        val claims = HashMap<String, Any>()
        claims.put(RestTokenUtil.CLAIM_KEY_USERNAME, TEST_USER)
        claims.put(RestTokenUtil.CLAIM_KEY_AUDIENCE, "testAudience")
        claims.put(RestTokenUtil.CLAIM_KEY_CREATED, DateUtil.parseDatetime(creationDate))
        claims.put(RestTokenUtil.CLAIM_ROLES, arrayListOf(SimpleGrantedAuthority(AuthorityName.ROLE_ADMIN.name)))
        return claims
    }

    private fun createToken(): String {
        val device = DeviceMock()
        device.isNormal = true

        return restTokenUtil.generateToken(
                UndUserDetails(
                        id = 1L,
                        username = TEST_USER,
                        secret = "secret",
                        key = "key",
                        password = "",
                        clientId = 1,
                        authorities = arrayListOf(SimpleGrantedAuthority(AuthorityName.ROLE_ADMIN.name), SimpleGrantedAuthority(AuthorityName.ROLE_EVENT.name))
                ), device)
    }

    companion object {

        private val TEST_USER = "testUser"
    }

}