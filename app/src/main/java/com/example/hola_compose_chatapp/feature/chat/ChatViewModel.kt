package com.example.hola_compose_chatapp.feature.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.useCases.GetCurrentUserUseCase
import com.example.hola_compose_chatapp.useCases.GetUserMessagesUseCase
import com.example.hola_compose_chatapp.useCases.SendMessageUseCase
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    firestoreRepository: FirestoreRepository,
    authRepository: AuthRepository,
    roomRepository: RoomRepository
) : ViewModel() {
    companion object {
        var receiverUser = UserModel("101", "MAc", "")
    }

    private val _isOpenHomeList = MutableLiveData<Boolean>(false)
    val isOpenHomeList = _isOpenHomeList
    private val _messageSendStatus = MutableLiveData<Either<Any>>()
    val messageSendStatus = _messageSendStatus
    private val _receiveMessageStatus = MutableLiveData<List<MessageModel>?>()
    val receiveMessageStatus = _receiveMessageStatus

    private val sendMessageUseCase = SendMessageUseCase(firestoreRepository)
    private val getUserMessagesUseCase = GetUserMessagesUseCase(roomRepository)
    private val getCurrentUserUseCase =
        GetCurrentUserUseCase(firestoreRepository, authRepository)


    fun openHomeList() {
        _isOpenHomeList.postValue(true)
    }

    fun sendMessage(message: String) {
        Log.d("SendMessage", "Model")
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getCurrentUserUseCase.execute()) {
                is Either.Success -> {
                    Log.d("SendMessage", "userINFoSUCCESS")
                    val msgModel = MessageModel(
                        UUID.randomUUID().toString(),
                        message,
                        Calendar.getInstance().time.toString(),
                        System.currentTimeMillis().toInt(),
                        response.data,
                        true,
                        receiverUser,
                        null
                    )
                    sendMessageUseCase.execute(msgModel)
                }

                is Either.Failed -> {
                    Log.d("SendMessage", "userINFoFailed")
                    _messageSendStatus.postValue(Either.Failed(""))
                }
            }
        }
    }

    fun getUserMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getUserMessagesUseCase.execute(receiverUser)) {
                is Either.Success -> {
                    _receiveMessageStatus.postValue(response.data)
                }

                is Either.Failed -> {
                    _receiveMessageStatus.postValue(listOf())
                }
            }
        }
    }
}