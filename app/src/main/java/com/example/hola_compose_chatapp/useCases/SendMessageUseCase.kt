package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.utils.Either

class SendMessageUseCase(private val firestoreRepository: FirestoreRepository) {

    suspend fun execute(messageModel: MessageModel): Either<Boolean> {
        return when (val response = firestoreRepository.sendMessages(messageModel)) {
            is Either.Success -> {
                Either.Success(response.data)
            }

            is Either.Failed -> {
                Either.Failed(response.msg)
            }
        }
    }
}