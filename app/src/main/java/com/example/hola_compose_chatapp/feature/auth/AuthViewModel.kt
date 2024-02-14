package com.example.hola_compose_chatapp.feature.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.useCases.UserSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: UserSignUpUseCase
):ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess = _loginSuccess


    fun signUp(email:String,password:String)
    {
       viewModelScope.launch(Dispatchers.IO){
           signUpUseCase.execute(email,password)
       }
    }


}