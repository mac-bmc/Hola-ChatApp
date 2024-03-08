package com.example.hola_compose_chatapp.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hola_compose_chatapp.model.ExecutiveModel
import com.example.hola_compose_chatapp.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToUser(users:List<ExecutiveModel>)

    @Query("DELETE FROM  HolaUsers")
    suspend fun deleteFromUser()
}