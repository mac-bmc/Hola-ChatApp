package com.example.hola_compose_chatapp.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.helper.AESEncryptionHelper
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.useCases.GetCurrentUserUseCase
import com.example.hola_compose_chatapp.useCases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    firestoreDataRepository: FirestoreRepository,
    authRepository: AuthRepository
) :
    ViewModel() {

    private val _chatViewOpen = MutableLiveData<Boolean>(false)
    val chatViewOpen = _chatViewOpen
    private var encryptionHelper = AESEncryptionHelper()

    private val sendMessageUseCase = SendMessageUseCase(firestoreDataRepository)
    private val getCurrentUserUseCase =
        GetCurrentUserUseCase(firestoreDataRepository, authRepository)


    fun openChatView() {
        _chatViewOpen.postValue(true)
    }

    fun resetOpenState() {
        _chatViewOpen.postValue(false)
    }

    fun encrypt() {
        encryptionHelper.encrypt("Helooooo")
    }

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}