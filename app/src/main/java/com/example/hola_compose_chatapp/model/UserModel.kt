package com.example.hola_compose_chatapp.model

import java.io.Serializable

data class UserModel(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userImg: String,
    val phNo: String,
    val address: String
) : Serializable {
    constructor() : this("", "", "", "", "", "")
}

