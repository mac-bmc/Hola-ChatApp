package com.example.hola_compose_chatapp.helper


import android.util.Log
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class AESEncryptionHelper {

    private var mySecretKey: SecretKey? = null
    private var myInitializationVector: ByteArray? = null

    fun encrypt(strToEncrypt: String): ByteArray {

        val plainText = strToEncrypt.toByteArray(Charsets.UTF_8)
        val keygen = KeyGenerator.getInstance("AES")
        keygen.init(256)

        val key = keygen.generateKey()
        mySecretKey = key

        val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val cipherText = cipher.doFinal(plainText)
        myInitializationVector = cipher.iv
        Log.d("encryptionKey",mySecretKey?.encoded.contentToString())
        Log.d("encryptionIv",myInitializationVector.contentToString())
        Log.d("encryptionRes",cipherText.contentToString())

        return cipherText
    }

}