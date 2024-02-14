package com.example.hola_compose_chatapp.useCases

import android.util.Log
import androidx.compose.runtime.saveable.autoSaver
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.utils.Either
import javax.inject.Inject

class UserSignUpUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {

    suspend fun execute(email:String,password:String)
    {
        when(val response = authRepository.signUp(email,password))
        {
            is Either.Success-> {
                Log.d("auth","success")
            }
             is Either.Failed ->{
                 Log.d("auth","failed")
             }
        }
    }
}