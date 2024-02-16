package com.example.hola_compose_chatapp.repositories

import android.util.Log
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.utils.Either
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface FirebaseRepository {
    suspend fun addToUsers(user: UserModel): Either<Boolean>
}

class FirebaseDataRepository
@Inject constructor(private val firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth) :
    FirebaseRepository {

    private val currentUser = firebaseAuth.currentUser
    override suspend fun addToUsers(user: UserModel): Either<Boolean> {
        var status: Either<Boolean> = Either.Success(true)
        Log.d("currentUser", currentUser?.email.toString())
        val doc = firestore.collection("users").document(user.uid)
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
}