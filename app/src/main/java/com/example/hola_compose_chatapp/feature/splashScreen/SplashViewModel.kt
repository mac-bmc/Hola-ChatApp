package com.example.hola_compose_chatapp.feature.splashScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.useCases.IsUserLoggedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(authRepository: AuthRepository) :ViewModel(){

    private val isUserLoggedIn = IsUserLoggedIn(authRepository)

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn = _isLoggedIn

    fun isLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.postValue(isUserLoggedIn.execute())
        }

    }
}