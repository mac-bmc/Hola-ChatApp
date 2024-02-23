package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Either

class SyncAllUsersUseCase(private val firestoreRepository: FirestoreRepository
                          , private val roomRepository: RoomRepository) {
    suspend fun execute() : Either<Any>
    {
        return when(val response = firestoreRepository.getAllUsers()) {
            is Either.Failed-> Either.Failed(response.msg)
            is Either.Success -> when(val syncStatus = roomRepository.syncHolaUsersData(response.data)) {
                is Either.Success -> {
                    Either.Success(syncStatus.data)
                }

                is Either.Failed -> {
                    Either.Failed(syncStatus.msg)
                }
            }
        }
    }
}