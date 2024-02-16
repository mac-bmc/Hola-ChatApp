package com.example.hola_compose_chatapp.di

import com.example.hola_compose_chatapp.repositories.FirebaseDataRepository
import com.example.hola_compose_chatapp.repositories.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {
    @Provides
    @Singleton
    fun provideFirestore():FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirestoreRepository(repository:FirebaseDataRepository):FirebaseRepository = repository
}