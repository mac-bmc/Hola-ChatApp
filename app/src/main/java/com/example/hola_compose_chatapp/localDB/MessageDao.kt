package com.example.hola_compose_chatapp.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hola_compose_chatapp.model.MessageModel


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun receiveMessage(messageList: List<MessageModel>)

    @Query("SELECT * FROM  MessageReciever WHERE _sendUser_uid=:secondUserUUID OR _sendUser_uid=:currentUserID ORDER BY timeStamp ASC")
    suspend fun getMessages(secondUserUUID: String, currentUserID: String): List<MessageModel>

    @Query("DELETE FROM MessageReciever")
    suspend fun deleteMessages()

    @Query("SELECT * FROM MessageReciever ORDER BY timeStamp ASC")
    suspend fun getAllMessages():List<MessageModel>
}