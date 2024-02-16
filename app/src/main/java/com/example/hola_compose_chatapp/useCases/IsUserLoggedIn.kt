package com.example.hola_compose_chatapp.useCases

import com.example.hola_compose_chatapp.repositories.AuthRepository

class IsUserLoggedIn(private val authRepository: AuthRepository) {

    suspend fun execute():Boolean
    {
        return authRepository.isLogged()

    }
}