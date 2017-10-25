package com.und.security.model.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("jwtKeys")
class JWTKeys {

    @Id
    lateinit var userId: String

    lateinit var secret: String

    lateinit var username: String

    lateinit var password: String

    var loginKey: String? = null

    var pswrdRstKey: String? = null

    var emailRgstnKey: String? = null


}