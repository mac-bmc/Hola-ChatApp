package com.example.hola_compose_chatapp.useCases

import android.util.Log
import com.example.hola_compose_chatapp.feature.home.HomeViewModel.Companion.mappedUser
import com.example.hola_compose_chatapp.repositories.FirestoreDataRepository.Companion.messageFlow
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class SyncReceivedMessageUseCase(
    private val firestoreRepository: FirestoreRepository,
    private val roomRepository: RoomRepository
) {

    val syncStatus = MutableStateFlow<Either<Any>>(Either.Failed("DB ERROR"))
    suspend fun execute(): Flow<Either<Any>> {

        return flow {
            when(val mapData = roomRepository.getMappingData())
            {
                is Either.Success->{
                    Log.d("SyncMessagesUIDUSE", mapData.data.toString())
                    mappedUser = mapData.data?.user
                    firestoreRepository.syncMessages(mapData.data?.user!!.userId)
                    messageFlow.collect { messageData ->
                        Log.d("SyncMessagesFROMMI", syncStatus.value.toString())
                        //syncStatus.value = roomRepository.syncMessageData(messageData)
                        emit(roomRepository.syncMessageData(messageData))
                        Log.d("SyncMessagesFROMMI", syncStatus.value.toString())
                    }
                }
                is Either.Failed->{
                    //syncStatus.value = Either.Failed("DB ERROR")
                    emit(Either.Failed("DB ERROR"))
                }
            }
        }



    }
}