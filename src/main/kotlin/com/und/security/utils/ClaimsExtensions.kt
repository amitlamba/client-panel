package com.und.security.utils

import com.und.common.utils.DateUtils
import com.und.security.model.AuthorityName
import io.jsonwebtoken.Claims
import java.util.*

val Claims.username: String
    get() {
        return this.subject
    }

val Claims.creationDate: Date
    get() {
        return Date(this[RestTokenUtil.CLAIM_KEY_CREATED] as Long)
    }

val Claims.expirationDate: Date
    get() {
        return this.expiration
    }

val Claims.claimedAudience: String
    get() {
        return this[RestTokenUtil.CLAIM_KEY_AUDIENCE] as String
    }
val Claims.isTokenExpired: Boolean
    get() {
        return this.expiration.before(DateUtils().now())
    }

val Claims.ignoreTokenExpiration: Boolean
    get() {
        return RestTokenUtil.AUDIENCE_TABLET == this.claimedAudience || RestTokenUtil.AUDIENCE_MOBILE == this.claimedAudience
    }

val Claims.roles :ArrayList<AuthorityName>
    get() {
        return this[RestTokenUtil.CLAIM_ROLES] as ArrayList<AuthorityName>
    }


val Claims.clientId :Long?
    get() {
        return this[RestTokenUtil.CLAIM_CLIENT_ID] as Long?
    }

val Claims.userId :Long?
    get() {
        return this[RestTokenUtil.CLAIM_USER_ID] as Long?
    }