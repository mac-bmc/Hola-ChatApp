package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.repositories.RoomRepository
import com.example.hola_compose_chatapp.utils.Conversions
import com.example.hola_compose_chatapp.utils.Either

class GetChatItemViewUseCase(val roomRepository: RoomRepository) {
    suspend fun execute():Either<List<ChatItemModel>>
    {
       when(val messages = roomRepository.getAllMessages())
       {
           is Either.Success->{

               return Either.Success(getChatItemList(messages.data))

           }
           is Either.Failed ->{
               return Either.Failed(messages.msg)
           }
       }
    }

    private fun getChatItemList(messageList:List<MessageModel>):List<ChatItemModel>
    {
        val chatItemList = mutableListOf<ChatItemModel>()
        messageList.forEach{
            messageModel->
            chatItemList.add(Conversions().toChatItemModel(messageModel))
        }
        return chatItemList
    }

    /*fun getDistinctChatList(chatViewList:List<ChatItemModel>):List<ChatItemModel>
    {
        for(chatModel in chatViewList)
        {

        }
    }*/
}