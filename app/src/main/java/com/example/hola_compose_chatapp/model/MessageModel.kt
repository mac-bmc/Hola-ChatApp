package com.example.hola_compose_chatapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MessageReciever")
data class MessageModel(
    @PrimaryKey
    val msgId: String = "",
    val content: String = "",
    val sentTime: String = "",
    val timeStamp: Int? = 0,
    @Embedded(prefix = "_sendUser_")
    val sendUser: UserModel = UserModel("", "", ""),
    val isSendByMe: Boolean = false,
    @Embedded
    val receiveUser: UserModel = UserModel("", "", ""),
    val readStatus: Boolean? = false
)

