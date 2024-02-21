package com.example.hola_compose_chatapp.feature.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.repositories.FirestoreRepository
import com.example.hola_compose_chatapp.useCases.AddUserToDBUseCase
import com.example.hola_compose_chatapp.useCases.IsUserLoggedIn
import com.example.hola_compose_chatapp.useCases.UserLoginUseCase
import com.example.hola_compose_chatapp.useCases.UserSignUpUseCase
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    authRepository: AuthRepository,
    firestoreRepository: FirestoreRepository
) : ViewModel() {
    private val signUpUseCase = UserSignUpUseCase(authRepository)
    private val loginUseCase = UserLoginUseCase(authRepository)

    private val addUserToDbUseCase = AddUserToDBUseCase(firestoreRepository)
    private val isUserLoggedIn = IsUserLoggedIn(authRepository)
    private val _signUpState = MutableLiveData<Either<String>>()
    val signUpState = _signUpState
    private val _loginState = MutableLiveData<Either<String>>()
    val loginState = _loginState
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn = _isLoggedIn


    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = signUpUseCase.execute(email, password)) {
                is Either.Success -> {
                    _signUpState.postValue(response)
                    addUserToDB(UserModel(UUID.randomUUID().toString(),email,""))
                }
                is Either.Failed ->{
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

    fun addUserToDB(userModel: UserModel) {
          viewModelScope.launch(Dispatchers.IO){
                addUserToDbUseCase.execute(userModel)
          }
    }


}