package com.example.hola_compose_chatapp.repositories

import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.utils.Either

interface FirebaseRepository {
    suspend fun addToUsers(user:UserModel):Either<Boolean>
}