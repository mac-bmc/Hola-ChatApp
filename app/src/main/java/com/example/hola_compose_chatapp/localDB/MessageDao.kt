package com.example.hola_compose_chatapp.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hola_compose_chatapp.model.MessageModel


@Dao
interface MessageDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun receiveMessage(messageList:List<MessageModel>)

    @Query("SELECT * FROM  MessageReciever WHERE _sendUser_uid=:secondUserUUID")
    suspend fun getMessages(secondUserUUID:String):List<MessageModel>
}