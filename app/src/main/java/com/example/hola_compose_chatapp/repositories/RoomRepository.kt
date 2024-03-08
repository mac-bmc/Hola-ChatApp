package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.localDB.MappingDao
import com.example.hola_compose_chatapp.localDB.MessageDao
import com.example.hola_compose_chatapp.localDB.UserDao
import com.example.hola_compose_chatapp.model.ExecutiveModel
import com.example.hola_compose_chatapp.model.MappedExecModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.utils.Either
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

interface RoomRepository {

    suspend fun syncHolaUsersData(userList: List<ExecutiveModel>): Either<Any>
    suspend fun syncMessageData(messageList: List<MessageModel>): Either<Any>

    suspend fun getUserMessage(senderUser: UserModel): Either<List<MessageModel>>
    suspend fun getAllMessages(): Either<List<MessageModel>>
    suspend fun syncMappingData(mappingData: MappedExecModel): Either<Boolean>
    suspend fun getMappingData(): Either<MappedExecModel?>
}

class RoomDataRepository @Inject constructor(
    private val userDao: UserDao,
    private val messageDao: MessageDao,
    private val mappingDao: MappingDao,
    private val firebaseAuth: FirebaseAuth
) : RoomRepository {

    private val currentUser = firebaseAuth.currentUser
    override suspend fun syncHolaUsersData(userList: List<ExecutiveModel>): Either<Any> {
        return try {
            userDao.deleteFromUser()
            userDao.insertToUser(userList)
            Either.Success("")
        } catch (_: Exception) {
            Either.Failed("")
        }
    }

    override suspend fun syncMessageData(messageList: List<MessageModel>): Either<Any> {
        return try {
            messageDao.deleteMessages()
            messageDao.receiveMessage(messageList)
            Log.d("SyncData", messageList.toString())
            Either.Success("")
        } catch (e: Exception) {
            Either.Failed("")
        }
    }

    override suspend fun getUserMessage(senderUser: UserModel): Either<List<MessageModel>> {
        return try {
            val messageList = messageDao.getMessages(senderUser.userId, currentUser!!.uid)
            Either.Success(messageList)
        } catch (e: Exception) {
            Either.Failed(e.toString())
        }
    }

    override suspend fun getAllMessages(): Either<List<MessageModel>> {
        return try {
            val messageList = messageDao.getAllMessages()
            Either.Success(messageList)
        } catch (e: Exception) {
            Log.d("ITEM-LIST", e.toString())
            Either.Failed(e.toString())
        }
    }

    override suspend fun syncMappingData(mappingData: MappedExecModel): Either<Boolean> {
        return try {
            mappingDao.deleteMappingData()
            mappingDao.syncMapping(mappingData)
            Log.d("SyncData", mappingData.toString())
            Either.Success(true)
        } catch (e: Exception) {
            Either.Failed("")
        }
    }

    override suspend fun getMappingData(): Either<MappedExecModel?> {
        return try {
            val map = mappingDao.getMappingData()
            Either.Success(map)
        } catch (e: Exception) {
            Log.d("ITEM-LIST", e.toString())
            Either.Failed(e.toString())
        }
    }

}