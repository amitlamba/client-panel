package com.und.common.utils

import org.springframework.beans.factory.annotation.Value
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


private val aes = "AES"
private var keyDefault = "BaO1TXt5B0R92Yys" // 128 bit key


fun encrypt(stringToEncrypt: String, key: String? = null): String? {
// Create key and cipher
    var keyVal: String?
    keyVal = key ?: keyDefault
    println(keyVal)
    val aesKey = SecretKeySpec(keyVal.toByteArray(), aes)
    val cipher = Cipher.getInstance(aes)
    var encrypted: String? = null
    // encrypt the text
    cipher.init(Cipher.ENCRYPT_MODE, aesKey)
    val encryptedByteArray = cipher.doFinal(stringToEncrypt.toByteArray())
    encrypted = String(Base64.getEncoder().encode(encryptedByteArray))
    return encrypted
}

fun decrypt(stringToDecrypt: String, key: String? = null): String? {
// Create key and cipher
    var keyVal: String?
    keyVal = key ?: keyDefault
    val aesKey = SecretKeySpec(keyVal.toByteArray(), aes)
    val cipher = Cipher.getInstance(aes)
    var decrypted: String? = null
    // decrypt the text
    cipher.init(Cipher.DECRYPT_MODE, aesKey)
    decrypted = String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt.toByteArray())))
    return decrypted
}