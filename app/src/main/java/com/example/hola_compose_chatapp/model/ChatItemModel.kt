package com.example.hola_compose_chatapp.model

data class ChatItemModel(
    val senderInfo : UserModel,
    val msgList : List<String>,
    val lastMsg : String,
    val lastMsgTime : String
)
