package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.utils.Either
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Either<String>
    suspend fun login(email: String, password: String): Either<String>
    suspend fun isLogged():Boolean
     fun getCurrentUserId():String
}


class AuthDataRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Either<String> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Either.Success("Success")
        } catch (e: Exception) {
            Log.d("authExc", e.toString())
            Either.Failed(e.toString())
        }
    }

    override suspend fun login(email: String, password: String): Either<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email.trim(), password.trim()).await()
            Either.Success("Success")
        } catch (e: Exception) {
            Log.d("authExc", e.toString())
            Either.Failed(e.toString())
        }
    }

    override suspend fun isLogged(): Boolean {
        return try {
            firebaseAuth.currentUser!=null
        }
        catch (e:Exception)
        {
            Log.d("isLogged",e.toString())
            false
        }
    }

    override  fun getCurrentUserId(): String {
        return firebaseAuth.currentUser!!.uid
    }

}