package com.example.hola_compose_chatapp.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {

    private val _chatViewOpen = MutableLiveData<Boolean>(false)
    val chatViewOpen = _chatViewOpen



    fun openChatView()
    {
        _chatViewOpen.postValue(true)
    }

    fun resetOpenState() {
        _chatViewOpen.postValue(false)
    }
}