package com.example.hola_compose_chatapp.localDB


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {
    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migration1to2()
        )

    private fun migration1to2(): Migration = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE MessageReciever ADD COLUMN timeStamp Int Default 0")
        }
    }
}