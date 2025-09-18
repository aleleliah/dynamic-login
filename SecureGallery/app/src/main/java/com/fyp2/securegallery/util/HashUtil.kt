package com.fyp2.securegallery.util

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import android.util.Base64

object HashUtil {
    private const val ITERATIONS = 10000
    private const val KEY_LENGTH = 256

    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        SecureRandom().nextBytes(salt)
        return salt
    }

    fun hash(value: String, salt: ByteArray): String {
        val spec = PBEKeySpec(value.toCharArray(), salt, ITERATIONS, KEY_LENGTH)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val hash = factory.generateSecret(spec).encoded
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }

    fun encodeSalt(salt: ByteArray): String = Base64.encodeToString(salt, Base64.NO_WRAP)

    fun decodeSalt(saltString: String): ByteArray = Base64.decode(saltString, Base64.NO_WRAP)
}
