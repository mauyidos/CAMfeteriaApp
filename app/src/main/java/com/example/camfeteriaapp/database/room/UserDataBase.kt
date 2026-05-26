package com.example.camfeteriaapp.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.camfeteriaapp.database.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao(): UserDataBaseDao
}