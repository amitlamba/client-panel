package com.und.common.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory


//FIXME add extensions for logger and try to use idiomatic property delegates
fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

fun Logger.debugT(msg: String) = if (isDebugEnabled) this.debug(msg) else Unit

fun usernameFromEmailAndType(email: String, userType: Int) = when (userType) {
    1 -> email
    2 -> "event_$email"
    else -> throw Exception("invalid user type")
}


