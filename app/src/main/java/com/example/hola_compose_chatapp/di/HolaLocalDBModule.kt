package com.example.hola_compose_chatapp.di

import android.app.Application
import android.content.Context
import com.example.hola_compose_chatapp.localDB.HolaLocalDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HolaLocalDBModule {


    @Provides
    fun provideApplicationContext(application: Application): Context =application.applicationContext
    @Provides
    fun provideHolaDb(application: Application) = HolaLocalDb.getDatabase(application)

    @Provides
    fun provideMessageDao(database:HolaLocalDb) = database.messageDao()

}