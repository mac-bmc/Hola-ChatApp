package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.utils.Either
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Either<Boolean>
    suspend fun login(username: String, password: String): Either<Boolean>
}


class AuthDataRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Either<Boolean> {
        return try {
           val authResult =  firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Either.Success(true)
        }
        catch (e:Exception)
        {
            Log.d("authExc",e.toString())
            Either.Failed(e.toString())
    }
}

override suspend fun login(username: String, password: String): Either<Boolean> {
    TODO("Not yet implemented")
}

}