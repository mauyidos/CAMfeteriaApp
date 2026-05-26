package com.example.camfeteriaapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class User(
    @PrimaryKey(true)
    val id: Int = 0,
    @ColumnInfo("name")
    val nombre: String,
    @ColumnInfo("email")
    val correo: String,
    @ColumnInfo("password")
    val password: String
)