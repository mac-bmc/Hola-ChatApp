package com.example.hola_compose_chatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MessageReciever")
data class MessageModel(
    @PrimaryKey
    val msgId: String = "",
    val content: String = "",
    val sentTime: String = "",
    val timeStamp: Int? = 0,
    val sendUserId: String = "",
    val isSendByMe: Boolean = false,
    val receiveUserId: String = ""
): Serializable {
    constructor() : this("","", "",0,"",false,"")
}

