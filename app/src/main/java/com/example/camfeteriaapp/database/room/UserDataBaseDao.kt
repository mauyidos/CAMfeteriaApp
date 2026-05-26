package com.example.camfeteriaapp.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.camfeteriaapp.database.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataBaseDao {
    //Crud
    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<User>>
    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Long): Flow<User>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}