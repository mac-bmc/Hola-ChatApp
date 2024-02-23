package com.example.hola_compose_chatapp.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel


@Database(entities = [MessageModel::class,UserModel::class], version = 2, exportSchema = false)

abstract class HolaLocalDb : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun userDao():UserDao
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
                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}