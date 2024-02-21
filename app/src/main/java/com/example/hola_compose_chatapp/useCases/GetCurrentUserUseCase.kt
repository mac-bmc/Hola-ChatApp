package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.utils.Either

class GetCurrentUserUseCase(
    private val firestoreRepository: FirestoreRepository,
    private val authRepository: AuthRepository
) {

    suspend fun execute(): Either<UserModel> {
        return when (val response = firestoreRepository.getUserInfo(executeGetUserUid())) {
            is Either.Failed -> {
                Either.Failed(response.msg)
            }

            is Either.Success -> {
                if (response.data != null)
                    Either.Success(response.data)
                else
                    Either.Failed("DB ERROR")
            }
        }
    }

    private fun executeGetUserUid(): String {
        return authRepository.getCurrentUserId()
    }
}