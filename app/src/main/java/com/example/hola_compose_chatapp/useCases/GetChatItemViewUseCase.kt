package com.example.hola_compose_chatapp.useCases

import android.util.Log
import com.example.hola_compose_chatapp.feature.home.HomeViewModel.Companion.mappedUser
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Conversions
import com.example.hola_compose_chatapp.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetChatItemViewUseCase(val roomRepository: RoomRepository) {
    suspend fun execute(): Flow<Either<List<ChatItemModel>>> {
        return flow {
            when (val messages = roomRepository.getAllMessages()) {
                is Either.Success -> {
                    Log.d("ChatMessageUseCase",messages.data.toString())
                    emit(Either.Success(getChatItemList(messages.data)))

                }

                is Either.Failed -> {
                    Log.d("ChatMessage","fail")
                    emit(Either.Failed(messages.msg))
                }


            }
        }
    }

    private fun getChatItemList(messageList: List<MessageModel>): List<ChatItemModel> {
        val chatList = mutableListOf<ChatItemModel>()
        if(messageList.isNotEmpty())
        {

            chatList.add(Conversions().toChatItemModel(messageList[messageList.size - 1],mappedUser))
            Log.d("ChatList",chatList.toString())
        }
        return chatList
    }


}