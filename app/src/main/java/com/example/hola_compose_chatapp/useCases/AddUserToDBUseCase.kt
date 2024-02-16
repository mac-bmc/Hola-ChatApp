package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.FirebaseRepository
import com.example.hola_compose_chatapp.utils.Either

class AddUserToDBUseCase(private val firebaseRepository: FirebaseRepository) {
    suspend fun execute(userModel: UserModel): Either<Boolean>
    {
        return when(val  response = firebaseRepository.addToUsers(userModel))
        {
            is Either.Success->{Either.Success(response.data)}
            is Either.Failed->{Either.Failed(response.msg)}

        }
    }
}