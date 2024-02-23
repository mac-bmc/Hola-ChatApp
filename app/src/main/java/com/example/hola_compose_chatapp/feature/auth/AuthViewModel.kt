package com.example.hola_compose_chatapp.feature.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.useCases.AddUserToDBUseCase
import com.example.hola_compose_chatapp.useCases.GetCurrentUserUseCase
import com.example.hola_compose_chatapp.useCases.IsUserLoggedIn
import com.example.hola_compose_chatapp.useCases.SyncAllUsersUseCase
import com.example.hola_compose_chatapp.useCases.SyncReceivedMessageUseCase
import com.example.hola_compose_chatapp.useCases.UserLoginUseCase
import com.example.hola_compose_chatapp.useCases.UserSignUpUseCase
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    authRepository: AuthRepository,
    firestoreRepository: FirestoreRepository,
    roomRepository: RoomRepository
) : ViewModel() {
    private val signUpUseCase = UserSignUpUseCase(authRepository)
    private val loginUseCase = UserLoginUseCase(authRepository)
    private val getCurrentUserUseCase = GetCurrentUserUseCase(firestoreRepository, authRepository)
    private val syncAllUsersUseCase = SyncAllUsersUseCase(firestoreRepository, roomRepository)
    private val syncReceivedMessageUseCase =
        SyncReceivedMessageUseCase(firestoreRepository, roomRepository)
    private val addUserToDbUseCase = AddUserToDBUseCase(firestoreRepository)
    private val isUserLoggedIn = IsUserLoggedIn(authRepository)
    private val _signUpState = MutableLiveData<Either<String>>()
    val signUpState = _signUpState
    private val _loginState = MutableLiveData<Either<String>>()
    val loginState = _loginState
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn = _isLoggedIn
    private val _userSyncStatus = MutableLiveData<Either<Any>>()
    val userSyncStatus = _userSyncStatus
    private val _messageSyncStatus = MutableLiveData<Either<Any>>()
    val messageSyncStatus = _messageSyncStatus


    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = signUpUseCase.execute(email, password)) {
                is Either.Success -> {
                    _signUpState.postValue(response)
                    syncHolaUsers()
                    addUserToDB(
                        UserModel(
                            getCurrentUserUseCase.executeGetUserUid(), email, ""
                        )
                    )
                }

                is Either.Failed -> {
                    _signUpState.postValue(response)
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.postValue(loginUseCase.execute(email, password))
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.postValue(isUserLoggedIn.execute())
        }

    }

    private fun addUserToDB(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserToDbUseCase.execute(userModel)
        }
    }

    fun syncHolaUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _userSyncStatus.postValue(syncAllUsersUseCase.execute())
        }
    }

    fun syncMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            _messageSyncStatus.postValue(syncReceivedMessageUseCase.execute())
        }
    }


}