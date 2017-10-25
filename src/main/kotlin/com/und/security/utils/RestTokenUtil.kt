package com.und.security.utils

import com.und.common.utils.DateUtils
import com.und.common.utils.loggerFor
import com.und.security.model.UndUserDetails
import com.und.security.service.JWTKeyService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mobile.device.Device
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class RestTokenUtil : Serializable {

    @Autowired
    lateinit private var dateUtils: DateUtils

    @Autowired
    lateinit private var keyResolver: KeyResolver

    @Autowired
    lateinit private var jwtKeyService: JWTKeyService

    @Value("\${security.expiration}")
    private var expiration: Long = 0


    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
                .setSigningKeyResolver(keyResolver)
                .parseClaimsJws(token)
                .body

    }


    fun validateToken(token: String): UndUserDetails? {
        val claims = getClaimsFromToken(token)
        return if(!claims.isTokenExpired) buildUserDetails(claims) else null

    }

    fun buildUserDetails(claims: Claims): UndUserDetails {
        val userId =  claims[CLAIM_USER_ID].toString().toLong()
        val jwtDetails = jwtKeyService.retrieveJwt(userId)
        return  UndUserDetails(
                id = userId,
                clientId = claims[CLAIM_CLIENT_ID].toString().toLong(),
                authorities = (claims[CLAIM_ROLES] as ArrayList<String>).map { role -> SimpleGrantedAuthority(role) },
                secret = jwtDetails.secret,
                username = jwtDetails.username,
                password = jwtDetails.password
        )
    }

    fun generateToken(userDetails: UndUserDetails, device: Device): String {

        fun doGenerateToken(claims: Map<String, Any>, expirationDate: Date): String {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, userDetails.secret)
                    .compact()
        }

        fun generateAudience(): String {
            return when {
                device.isNormal -> AUDIENCE_WEB
                device.isMobile -> AUDIENCE_MOBILE
                device.isTablet -> AUDIENCE_TABLET
                else -> AUDIENCE_UNKNOWN
            }
        }

        val createdDate = dateUtils.now()

        val claims = mapOf(
                CLAIM_KEY_USERNAME to userDetails.username,
                CLAIM_KEY_AUDIENCE to generateAudience(),
                CLAIM_USER_ID to userDetails.id.toString(),
                CLAIM_CLIENT_ID to userDetails.clientId.toString(),
                CLAIM_ROLES to userDetails.authorities.map { auth -> auth.authority },
                CLAIM_KEY_CREATED to createdDate
        )

        val expirationDate = Date(createdDate.time + expiration * 1000)
        return doGenerateToken(claims, expirationDate)
    }


    companion object {
        protected val logger = loggerFor(RestTokenUtil::class.java)
        private const val serialVersionUID = -3301605591108950415L

        internal val CLAIM_KEY_USERNAME = "sub"
        internal val CLAIM_KEY_AUDIENCE = "audience"
        internal val CLAIM_KEY_CREATED = "created"
        internal val CLAIM_KEY_EXPIRED = "exp"
        internal val CLAIM_ONE_TIME = "onetime"
        internal val CLAIM_ROLES = "roles"
        internal val CLAIM_CLIENT_ID = "clientId"
        internal val CLAIM_USER_ID = "userId"
        internal val AUDIENCE_UNKNOWN = "unknown"
        internal val AUDIENCE_WEB = "web"
        internal val AUDIENCE_MOBILE = "mobile"
        internal val AUDIENCE_TABLET = "tablet"
    }
}