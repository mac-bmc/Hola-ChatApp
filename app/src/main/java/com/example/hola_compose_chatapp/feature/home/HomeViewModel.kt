package com.example.hola_compose_chatapp.feature.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.useCases.GetChatItemViewUseCase
import com.example.hola_compose_chatapp.useCases.SyncExecMappingUseCase
import com.example.hola_compose_chatapp.useCases.SyncReceivedMessageUseCase
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    firestoreDataRepository: FirestoreRepository,
    authRepository: AuthRepository,
    roomRepository: RoomRepository
) : ViewModel() {


    companion object{
        var mappedUser : UserModel? = null
    }

    private val syncExecMappingUseCase =
        SyncExecMappingUseCase(roomRepository, firestoreDataRepository)
    private val _chatViewOpen = MutableLiveData(false)
    val chatViewOpen = _chatViewOpen


    private val _messageSyncStatus = MutableLiveData<Either<Any>>()
    val messageSyncStatus = _messageSyncStatus


    private val syncReceivedMessageUseCase =
        SyncReceivedMessageUseCase(firestoreDataRepository, roomRepository)
    private val getChatItemViewUseCase = GetChatItemViewUseCase(roomRepository)

    private val _mapSyncStatus = MutableLiveData(false)
    val mapSyncStatus = _mapSyncStatus

    private val _chatListView = MutableLiveData<List<ChatItemModel>>(listOf())
    val chatListView = _chatListView


    fun openChatView() {
        _chatViewOpen.postValue(true)
    }

    fun resetOpenState() {
        _chatViewOpen.postValue(false)
    }


    fun syncMessages() {
        Log.d("SyncMessagesVM", "Called")
        viewModelScope.launch(Dispatchers.IO) {
            syncReceivedMessageUseCase.execute().collect {
                _messageSyncStatus.postValue(it)
                Log.d("SyncMessagesVMColl", messageSyncStatus.value.toString())
            }

        }
    }


    fun getChatItem() {
        viewModelScope.launch(Dispatchers.IO) {
            getChatItemViewUseCase.execute().collect {
            when (it) {
                is Either.Success -> {
                    _chatListView.postValue(it.data!!)
                    Log.d("ChatMessageVM", it.data.toString())
                }

                else -> {
                    Log.d("ChatMessage", "gii")
                }
            }
        }
        }
    }

    fun syncEzKartMapping() {
        viewModelScope.launch(Dispatchers.IO) {
            syncExecMappingUseCase.execute().collect {
                _mapSyncStatus.postValue(it)
                Log.d("SyncMapVM", mapSyncStatus.value.toString())
                Log.d("SyncMapVMC", it.toString())
            }/*syncMapStatus.collect {

            }*/

        }
    }
}