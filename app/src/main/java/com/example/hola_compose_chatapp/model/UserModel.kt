package com.example.hola_compose_chatapp.model

import java.util.UUID

data class UserModel(
    val uid: String = UUID.randomUUID().toString(),
    val userName:String,
    val profileImage:String
)
