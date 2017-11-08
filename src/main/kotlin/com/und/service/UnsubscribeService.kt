package com.und.service

import com.und.common.utils.decrypt
import com.und.common.utils.encrypt
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class UnsubscribeService {

    data class UnsubscribeLinkParams(val emailAddress: String?, val clientID: Int, val userID: String?)

    private val separator = "|||"


    @Value("\${encryption-decryption.key.unsubscribe}")
    lateinit private var encryptDecryptKey: String

    fun createUnsubscribeLink(unsubscribeLinkParams: UnsubscribeLinkParams): String {
        val eString = encrypt(unsubscribeLinkParams.emailAddress + separator + unsubscribeLinkParams.clientID
                + separator + unsubscribeLinkParams.userID, key = encryptDecryptKey)
        return eString!!
    }

    fun getDataFromUnsubscribeLink(unsubscribeLink: String): UnsubscribeLinkParams {
        val dString = decrypt(stringToDecrypt = unsubscribeLink, key = encryptDecryptKey)
        val arr = dString!!.split(separator)
        return UnsubscribeLinkParams(arr[0], arr[1].toInt(), arr[2])
    }
}