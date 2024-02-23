package com.example.hola_compose_chatapp.di

import android.app.Application
import android.content.Context
import com.example.hola_compose_chatapp.localDB.HolaLocalDb
import com.example.hola_compose_chatapp.repositories.RoomDataRepository
import com.example.hola_compose_chatapp.repositories.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HolaLocalDBModule {


    @Provides
    fun provideApplicationContext(application: Application): Context =application.applicationContext
    @Provides
    fun provideHolaDb(application: Application) = HolaLocalDb.getDatabase(application)

    @Provides
    @Singleton
    fun provideMessageDao(database:HolaLocalDb) = database.messageDao()

    @Provides
    @Singleton
    fun provideUserDao(database:HolaLocalDb) = database.userDao()

    @Provides
    fun provideDBRepository(repo:RoomDataRepository):RoomRepository = repo

}