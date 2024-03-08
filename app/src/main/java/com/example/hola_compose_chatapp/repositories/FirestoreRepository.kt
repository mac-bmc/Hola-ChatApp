@file:Suppress("NAME_SHADOWING")

package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.model.ExecutiveModel
import com.example.hola_compose_chatapp.model.MappedExecModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.utils.Either
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface FirestoreRepository {
    suspend fun addToUsers(execModel: ExecutiveModel): Either<Boolean>
    suspend fun getAllUsers(): Either<List<ExecutiveModel>>
    suspend fun syncMessages(sendUserId: String)
    suspend fun getUserInfo(execId: String): Either<ExecutiveModel?>
    suspend fun sendMessages(messageModel: MessageModel): Either<Boolean>
    suspend fun getExecMap()

}

class FirestoreDataRepository
@Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) :
    FirestoreRepository {

    companion object {
        val messageFlow = MutableStateFlow<List<MessageModel>>(listOf())
        val mapFlow = MutableStateFlow<MappedExecModel?>(null)
    }

    private val currentUser = firebaseAuth.currentUser
    override suspend fun addToUsers(execModel: ExecutiveModel): Either<Boolean> {
        var status: Either<Boolean> = Either.Success(true)
        Log.d("currentUser", currentUser?.email.toString())
        val doc = firestore.collection("executive").document(firebaseAuth.currentUser!!.uid)
        try {
            val procedure = Tasks.await(
                doc.set(execModel)
                    .addOnSuccessListener {

                        Log.d("success", "$status")
                    }
                    .addOnFailureListener {
                        Log.e("toUser", "${it.message}")
                        status = Either.Failed("DB ERROR")

                    }
            )
            procedure.hashCode()
        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        return status
    }

    override suspend fun getAllUsers(): Either<List<ExecutiveModel>> {
        val userList = mutableListOf<ExecutiveModel>()
        var response = 0
        val doc = firestore.collection("EzKart-exe")
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
                    val data = doc.toObject(ExecutiveModel::class.java)
                    if (data != null) {
                        response = 200
                        userList.add(data)
                    }
                }
            }


        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        if (response != 200)
            return Either.Failed("DB ERROR")
        return Either.Success(userList)
    }

    override suspend fun syncMessages(sendUserId: String) {
        Log.d("SyncMessagesUID",sendUserId)
        val doc =
            firestore.collection("messages-executive").document(firebaseAuth.currentUser!!.uid)
                .collection(sendUserId)

        try {
            doc.addSnapshotListener { value, error ->
                error.let {
                    Log.d("SyncMessages", it.toString())
                }
                value.let {
                    Log.d("SyncMessages", it!!.documents.size.toString())
                    val data = value?.toObjects(MessageModel::class.java)
                    Log.d("SyncMessages", data.toString())
                    messageFlow.value = data!!


                }
            }
        } catch (e: Exception) {
            Log.d("SyncMessages", e.toString())
        }
    }

    override suspend fun getUserInfo(execId: String): Either<ExecutiveModel?> {
        var response = 0
        var exec: ExecutiveModel? = null
        val doc = firestore.collection("executive").document(execId)
        try {
            val receiveSnap = Tasks.await(
                doc.get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            it.result
                            response = 200
                        }
                    }

            )
            exec = receiveSnap.toObject(ExecutiveModel::class.java)


        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        Log.d("SendMessage", "@@userINFo" + exec.toString())
        if (response != 200)
            return Either.Failed("DB ERROR")
        return Either.Success(exec)
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
            send.hashCode()
        } catch (e: Exception) {
            Log.e("toUser", "$e")

        }
        return status


    }

    override suspend fun getExecMap() {
        var map: MappedExecModel? = null
        val doc = firestore.collection("mapping-exec-user").document(currentUser!!.uid)
        try {
            doc.addSnapshotListener { value, error ->
                error.let {
                    Log.d("ReceiveListERR", it.toString())
                }
                value.let {
                    val data = value?.toObject(MappedExecModel::class.java)
                    Log.d("ReceiveList!!!", data.toString())
                    mapFlow.value = data

                }
            }
        } catch (e: Exception) {
            Log.d("getMessage", e.toString())
        }
    }
}