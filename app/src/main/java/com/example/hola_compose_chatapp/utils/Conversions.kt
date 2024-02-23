package com.example.hola_compose_chatapp.utils

import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.MessageModel

class Conversions {

    fun toChatItemModel(messageModel: MessageModel): ChatItemModel {
        return ChatItemModel(messageModel.sendUser, messageModel.content)
    }
}