package com.devrapid.kotlinknifer.encrypt

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESEncrypt {
    fun encrypt(data: String, key: String) =
        Base64.encodeToString(ase256Encrypt(data, key), Base64.DEFAULT)

    fun decrypt(encrypted: String, key: String) =
        String(ase256Decrypt(Base64.decode(encrypted, Base64.DEFAULT), key))

    private fun ase256Encrypt(data: String, key: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(getCipherKey(key), "SHA-256")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, getIvSpec())
        return cipher.doFinal(data.toByteArray())
    }

    private fun ase256Decrypt(dataByte: ByteArray, key: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(getCipherKey(key), "SHA-256")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, getIvSpec())
        return cipher.doFinal(dataByte)
    }

    private fun getCipherKey(key: String): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        digest.update(key.toByteArray())
        val keyBytes = ByteArray(32)
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.size)

        return keyBytes
    }

    private fun getIvSpec() = IvParameterSpec(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
}
