package com.example.hola_compose_chatapp.utils

import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel

class Conversions {

    fun toChatItemModel(messageModel: MessageModel, userModel: UserModel?): ChatItemModel {
        return ChatItemModel(userModel!!, messageModel.content)
    }
}