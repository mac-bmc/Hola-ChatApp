package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.ExecutiveModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.utils.Either

class AddUserToDBUseCase(private val firestoreRepository: FirestoreRepository) {
    suspend fun execute(execModel: ExecutiveModel): Either<Boolean>
    {
        return when(val  response = firestoreRepository.addToUsers(execModel))
        {
            is Either.Success->{Either.Success(response.data)}
            is Either.Failed->{Either.Failed(response.msg)}

        }
    }
}