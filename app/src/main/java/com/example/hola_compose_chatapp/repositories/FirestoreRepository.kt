package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.utils.Either
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface FirestoreRepository {
    suspend fun addToUsers(user: UserModel): Either<Boolean>
    suspend fun getMessages(): Either<List<MessageModel>>
    suspend fun getUserInfo(userId: String): Either<UserModel?>
    suspend fun sendMessages(messageModel: MessageModel): Either<Boolean>
}

class FirestoreDataRepository
@Inject constructor(private val firestore: FirebaseFirestore, val firebaseAuth: FirebaseAuth) :
    FirestoreRepository {

    private val currentUser = firebaseAuth.currentUser
    override suspend fun addToUsers(user: UserModel): Either<Boolean> {
        var status: Either<Boolean> = Either.Success(true)
        Log.d("currentUser", currentUser?.email.toString())
        val doc = firestore.collection("users").document(firebaseAuth.currentUser!!.uid)
        try {
            val procedure = Tasks.await(
                doc.set(user)
                    .addOnSuccessListener {

                        Log.d("success", "$status")
                    }
                    .addOnFailureListener {
                        Log.e("toUser", "${it.message}")
                        status = Either.Failed("DB ERROR")

                    }
            )
            val x = procedure.hashCode()
        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        return status
    }

    override suspend fun getMessages(): Either<List<MessageModel>> {
        val messageList = mutableListOf<MessageModel>()
        var response = 0
        val doc = firestore.collection("messages")
        try {
            val receiveSnap = Tasks.await(
                doc.get()
                    .addOnCompleteListener {
                        if (it.isSuccessful)
                            it.result
                    }

            )
            receiveSnap.let {
                for (doc in it.documents) {
                    val data = doc.toObject(MessageModel::class.java)
                    if (data != null) {
                        response = 200
                        messageList.add(data)
                    }
                }
            }


        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        if (response != 200)
            return Either.Failed("DB ERROR")
        return Either.Success(messageList)
    }

    override suspend fun getUserInfo(userId: String): Either<UserModel?> {
        val response = 0
        var user: UserModel? = UserModel("", "", "")
        val doc = firestore.collection("users").document(userId)
        try {
            val receiveSnap = Tasks.await(
                doc.get()
                    .addOnCompleteListener {
                        if (it.isSuccessful)
                            it.result
                    }

            )
            user = receiveSnap.toObject(UserModel::class.java)


        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        if (response != 200)
            return Either.Failed("DB ERROR")
        return Either.Success(user)
    }

    override suspend fun sendMessages(messageModel: MessageModel): Either<Boolean> {
        var status: Either<Boolean> = Either.Success(true)
        Log.d("currentUser", currentUser?.email.toString())
        val doc = firestore.collection("messages").document(messageModel.msgId)
        try {
            val send = Tasks.await(
                doc.set(messageModel)
                    .addOnCompleteListener {
                        if (it.isSuccessful)
                            Log.d("success", "$status")
                    }
                    .addOnFailureListener {
                        Log.e("toMes", "${it.message}")
                        status = Either.Failed("DB ERROR")
                    }
            )
            val x = send.hashCode()
        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        return status


    }
}