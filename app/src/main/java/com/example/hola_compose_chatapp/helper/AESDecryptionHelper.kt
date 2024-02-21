package com.example.hola_compose_chatapp.helper


import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class AESDecryptionHelper(
    private val mySecretKey: SecretKey?,
    private val initializationVector: ByteArray?
) {

    fun decrypt(dataToDecrypt: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        val ivSpec = IvParameterSpec(initializationVector)
        cipher.init(Cipher.DECRYPT_MODE, mySecretKey, ivSpec)
        val cipherText = cipher.doFinal(dataToDecrypt)

        return cipherText
    }

}