package com.example.hola_compose_chatapp.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hola_compose_chatapp.model.MappedExecModel

@Dao

interface MappingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun syncMapping(mapList:MappedExecModel)

    @Query("DELETE FROM ExecutiveMapping")
    suspend fun deleteMappingData()

    @Query("SELECT * FROM ExecutiveMapping")
    suspend fun getMappingData():MappedExecModel
}