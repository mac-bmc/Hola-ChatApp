package com.example.hola_compose_chatapp.feature.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel:ViewModel() {
    private val _isOpenHomeList = MutableLiveData<Boolean>(false)
    val  isOpenHomeList =  _isOpenHomeList


    fun openHomeList()
    {
        _isOpenHomeList.postValue(true)
    }
}