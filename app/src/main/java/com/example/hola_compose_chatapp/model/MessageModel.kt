package com.example.hola_compose_chatapp.model


data class MessageModel(
    val msgId:String,
    val content:String,
    val sentTime:String,
    val sendUser:UserModel,
    val receiveUser:UserModel,
    val readStatus:Boolean
)

