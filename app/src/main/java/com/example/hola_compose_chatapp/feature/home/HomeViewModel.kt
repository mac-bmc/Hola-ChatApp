package com.example.hola_compose_chatapp.feature.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.helper.AESEncryptionHelper
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.useCases.GetChatItemViewUseCase
import com.example.hola_compose_chatapp.useCases.GetCurrentUserUseCase
import com.example.hola_compose_chatapp.useCases.SendMessageUseCase
import com.example.hola_compose_chatapp.useCases.SyncAllUsersUseCase
import com.example.hola_compose_chatapp.useCases.SyncReceivedMessageUseCase
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    firestoreDataRepository: FirestoreRepository,
    authRepository: AuthRepository,
    roomRepository: RoomRepository
) :
    ViewModel() {


    private val syncAllUsersUseCase = SyncAllUsersUseCase(firestoreDataRepository, roomRepository)
    private val _chatViewOpen = MutableLiveData<Boolean>(false)
    val chatViewOpen = _chatViewOpen



    private val _messageSyncStatus = MutableLiveData<Either<Any>>()
    val messageSyncStatus = _messageSyncStatus

    private var encryptionHelper = AESEncryptionHelper()


    private val syncReceivedMessageUseCase =
        SyncReceivedMessageUseCase(firestoreDataRepository, roomRepository)
    private val getChatItemViewUseCase = GetChatItemViewUseCase(roomRepository)

    private val _userSyncStatus = MutableLiveData<Either<Any>>()
    val userSyncStatus = _userSyncStatus

    private val _chatListView = MutableLiveData<List<ChatItemModel>>()
    val chatListView=_chatListView


    fun openChatView() {
        _chatViewOpen.postValue(true)
    }

    fun resetOpenState() {
        _chatViewOpen.postValue(false)
    }

    fun encrypt() {
        encryptionHelper.encrypt("Helooooo")
    }




    fun syncMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            _messageSyncStatus.postValue(syncReceivedMessageUseCase.execute())
        }
    }

    fun syncHolaUsers()
    {
        viewModelScope.launch(Dispatchers.IO){
            _userSyncStatus.postValue(syncAllUsersUseCase.execute())
        }
    }
    fun getChatItemList()
    {
      viewModelScope.launch(Dispatchers.IO)
      {
          when(val response = getChatItemViewUseCase.execute())
          {
              is Either.Success->{
                  chatListView.postValue(response.data)
              }
              else->{}
          }
      }
    }
}