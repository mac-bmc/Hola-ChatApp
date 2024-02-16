package com.example.hola_compose_chatapp.di

import com.example.hola_compose_chatapp.repositories.AuthDataRepository
import com.example.hola_compose_chatapp.repositories.AuthRepository
import com.example.hola_compose_chatapp.useCases.UserSignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth
    {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(impl:AuthDataRepository):AuthRepository = impl

}