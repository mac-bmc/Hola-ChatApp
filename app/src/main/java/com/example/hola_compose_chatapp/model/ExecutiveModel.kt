package com.example.hola_compose_chatapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "HolaUsers")
data class ExecutiveModel(
    @PrimaryKey
    val execId: String,
    val execName: String,
    val execEmail: String,
    val userImg: String,
    val isOccupied : Boolean
) : Serializable {
    constructor() : this("", "", "", "",false)
}


