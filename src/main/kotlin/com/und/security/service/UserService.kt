package com.und.security.service

import com.und.common.utils.usernameFromEmailAndType
import com.und.security.model.UndUserDetails
import com.und.security.model.User
import com.und.security.model.redis.JWTKeys
import com.und.security.repository.UserRepository
import com.und.security.utils.KEYTYPE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mobile.device.Device
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit private var jwtKeyService: JWTKeyService

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)

    }

    fun updateJwtOfEventUser(device: Device, adminUser: UndUserDetails): Int {
        //FIXME usernameFromEmailAndType method need fix and not required here
        val username = usernameFromEmailAndType(adminUser.username, 2)
        val jwt = generateJwtByUser(username, device)
        val updatedCount = userRepository.updateJwtOfEventUser(jwt.loginKey?:"", username)
        jwtKeyService.updateJwt(jwt)
        return updatedCount

    }


    private fun generateJwtByUser(username: String, device: Device): JWTKeys {
        // Reload password post-security so we can generate token
        val user = findByUsername(username)
        return if (user != null) {
            jwtKeyService.generateJwt(user, device, KEYTYPE.LOGIN)
        } else JWTKeys()
    }

    fun resetPassword(userDetails: UndUserDetails, password:String, jwtToken:JWTKeys) {
        userRepository.resetPassword(passwordEncoder.encode(password),
                userDetails.username)
        jwtToken.pswrdRstKey = null
        jwtToken.loginKey = null
        jwtKeyService.updateJwt(jwtToken)
    }

    fun resetPassword(userDetails: UndUserDetails, password:String) {
        val userId = userDetails.id
        if(userId!=null) {
            val jwtToken = jwtKeyService.retrieveJwt(userId)
            resetPassword(userDetails,password,jwtToken)
        }
    }

    fun generateJwtForForgotPassword(email: String, device: Device): String {
        return generateJwtByUser(email, device, KEYTYPE.PASSWORD_RESET).pswrdRstKey?:""
    }

    private fun generateJwtByUser(username: String, device: Device, keytype: KEYTYPE): JWTKeys {
        // Reload password post-security so we can generate token
        val user = findByUsername(username)
        return if (user != null) {
            jwtKeyService.generateJwt(user, device, keytype)
        } else JWTKeys()
    }
}