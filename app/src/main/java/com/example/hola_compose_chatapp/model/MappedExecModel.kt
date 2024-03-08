package com.example.hola_compose_chatapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ExecutiveMapping")
data class MappedExecModel(
    @PrimaryKey
    val mapID: String,
    @Embedded("_exec")
    val executive: ExecutiveModel? = null,
    @Embedded("_user")
    var user: UserModel? = null
) : Serializable {

    constructor() : this("", null, null)
}
