package com.und.security.service

import com.und.security.model.redis.JWTKeys
import com.und.repository.JWTKeyRepository
import com.und.security.model.Client
import com.und.security.model.UndUserDetails
import com.und.security.utils.RestTokenUtil
import com.und.security.utils.RestUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mobile.device.Device
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTKeyService {

    @Autowired
    lateinit var jwtKeyRepository: JWTKeyRepository

    @Autowired
    lateinit private var restTokenUtil: RestTokenUtil


    fun retrieveJwt(userId: Long): JWTKeys {
        val cacheKey = generateIdKey(userId)
        return getKeyIfExists(cacheKey)

    }

    fun generateJwt(user: UndUserDetails, device: Device): String {

        fun saveLoginKey(): JWTKeys {
            val cacheKey = generateIdKey(user.id!!)
            val jwt = getKeyIfExists(cacheKey)
            with(jwt) {
                userId = cacheKey
                loginKey = restTokenUtil.generateToken(user, device)
                this.secret = user.secret
                this.username = user.username
                this.password = user.password!!
            }
            jwtKeyRepository.save(jwt)
            return jwt
        }

        val jwtKey = saveLoginKey()
        return jwtKey.loginKey!!
    }


    private fun getKeyIfExists(cacheKey: String): JWTKeys {
        val savedJwt: JWTKeys? = jwtKeyRepository.findById(cacheKey).get()
        return if (savedJwt != null) {
            savedJwt
        } else {
            val key = JWTKeys()
            key.secret = ""
            key
        }


    }

    private fun generateIdKey(userId: Long): String = "$userId"
}
