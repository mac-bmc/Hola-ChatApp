package com.example.hola_compose_chatapp.useCases

import android.util.Log
import com.example.hola_compose_chatapp.repositories.FirestoreDataRepository.Companion.mapFlow
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class SyncExecMappingUseCase(
    val roomRepository: RoomRepository,
    val firestoreRepository: FirestoreRepository
) {

    companion object {
        val syncMapStatus = MutableStateFlow<Boolean>(false)
    }


    suspend fun execute(): Flow<Boolean> {
        return flow {
            firestoreRepository.getExecMap()
            //Log.d("SyncMapRoom", syncMapStatus.value.toString())
            mapFlow.collect {
                Log.d("SyncMap", it.toString())
                if (it != null) {
                    when (val syncStatus = roomRepository.syncMappingData(it)) {
                        is Either.Success -> {
                            /*Log.d("SyncMap", "fromRoomSuccess")
                            Log.d("SyncMapRoom1", syncMapStatus.value.toString())
                            *//*syncMapStatus.value = true
                            syncMapStatus.emit(true)*/
                            emit(true)
                            //Log.d("SyncMapRoom2", syncMapStatus.value.toString())
                        }

                        is Either.Failed -> {
                            //Log.d("SyncMap", "fromRoomFailed")
                            /*syncMapStatus.value = false
                            syncMapStatus.emit(false)*/
                            emit(false)
                        }
                    }
                }
            }

        }
        }
}