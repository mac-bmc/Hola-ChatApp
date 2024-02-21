package com.example.hola_compose_chatapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "MessageReciever")
data class MessageModel(
    @PrimaryKey
    val msgId:String = UUID.randomUUID().toString(),
    val content:String,
    val sentTime:String,
    @Embedded(prefix = "_sendUser_")
    val sendUser:UserModel,
    val isSendByMe:Boolean,
    @Embedded
    val receiveUser:UserModel,
    val readStatus:Boolean
)

