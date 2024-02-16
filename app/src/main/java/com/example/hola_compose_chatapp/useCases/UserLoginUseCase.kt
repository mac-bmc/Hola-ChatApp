
package com.example.hola_compose_chatapp.useCases

import android.util.Log
import androidx.compose.runtime.saveable.autoSaver
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.utils.Either
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun execute(email:String,password:String):Either<String>
    {
        return when(val response = authRepository.login(email,password)) {
            is Either.Success-> {
                Log.d("auth","success")
                Either.Success(response.data)
            }

            is Either.Failed ->{
                Log.d("auth","failed")
                Either.Failed(response.msg)

            }
        }
    }
}