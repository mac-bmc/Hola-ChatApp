package com.example.hola_compose_chatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.auth.User
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "HolaUsers")
data class UserModel(
    @PrimaryKey
    val uid: String="" ,
    val userName:String = "",
    val profileImage:String = ""
)
