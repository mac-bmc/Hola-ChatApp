package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Either

class SyncReceivedMessageUseCase(private val firestoreRepository: FirestoreRepository, private val roomRepository: RoomRepository) {

   suspend fun execute(): Either<Any>
    {
        return when(val response = firestoreRepository.getMessages())
        {
            is Either.Success -> when(val syncStatus = roomRepository.syncMessageData(response.data))
            {
                is Either.Success -> {
                    return Either.Success(syncStatus.data)
                }
                is Either.Failed ->{
                    Either.Failed(syncStatus.msg)
                }
            }
            is Either.Failed -> Either.Failed(response.msg)
        }
    }
}