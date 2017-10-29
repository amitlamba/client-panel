package com.und.security.service

import com.und.security.model.redis.JWTKeys
import com.und.repository.JWTKeyRepository
import com.und.security.model.UndUserDetails
import com.und.security.model.User
import com.und.security.utils.KEYTYPE
import com.und.security.utils.RestTokenUtil
import com.und.security.utils.RestUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mobile.device.Device
import org.springframework.stereotype.Service

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

    fun updateJwt(jwt: JWTKeys): JWTKeys {
        //TODO fix updating only what is required
        return jwtKeyRepository.save(jwt)

    }


    fun generateJwt(user: User, device: Device, keyType: KEYTYPE): JWTKeys {
        val userDetails = RestUserFactory.create(user)
        return generateJwt(userDetails, device, keyType)
    }

    fun generateJwt(user: UndUserDetails, device: Device, keyType: KEYTYPE): JWTKeys {

        fun buildKey(): JWTKeys {
            val cacheKey = generateIdKey(user.id!!)
            val jwt = getKeyIfExists(cacheKey)
            with(jwt) {
                userId = cacheKey
                when (keyType) {
                    KEYTYPE.LOGIN -> loginKey = restTokenUtil.generateToken(user, device)
                    KEYTYPE.PASSWORD_RESET -> pswrdRstKey = restTokenUtil.generateToken(user, device)
                    KEYTYPE.REGISTRATION -> emailRgstnKey = restTokenUtil.generateToken(user, device)
                }
                this.secret = user.secret
                this.username = user.username
                this.password = user.password!!
            }
            return jwt

        }

        val jwt = buildKey()
        jwtKeyRepository.save(jwt)
        return jwt
    }


    private fun getKeyIfExists(cacheKey: String): JWTKeys {
        val jwtOption = jwtKeyRepository.findById(cacheKey)
        return if (jwtOption.isPresent) {
            jwtOption.get()
        } else {
            val key = JWTKeys()
            key.secret = ""
            key
        }


    }

    private fun generateIdKey(userId: Long): String = "$userId"
}


