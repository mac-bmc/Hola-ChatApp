package com.example.hola_compose_chatapp.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hola_compose_chatapp.model.MessageModel


@Database(entities = [MessageModel::class], version = 1, exportSchema = false)

abstract class HolaLocalDb : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: HolaLocalDb? = null

        fun getDatabase(context: Context): HolaLocalDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HolaLocalDb::class.java,
                    "Hola_local_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}