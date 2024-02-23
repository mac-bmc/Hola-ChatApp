package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Either

class GetUserMessagesUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(sendUser: UserModel): Either<List<MessageModel>>
    {
        return when(val response = roomRepository.getUserMessage(sendUser)) {
            is Either.Success-> {
                Either.Success(response.data)
            }

            is Either.Failed -> {
                Either.Failed(response.msg)
            }
        }
    }
}