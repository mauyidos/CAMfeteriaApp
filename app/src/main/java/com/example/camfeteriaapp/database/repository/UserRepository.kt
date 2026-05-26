package com.example.camfeteriaapp.database.repository

import com.example.camfeteriaapp.database.model.User
import com.example.camfeteriaapp.database.room.UserDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository@Inject constructor(private val userDataBaseDao: UserDataBaseDao) {
    suspend fun addUser(user: User) = userDataBaseDao.insert(user)

    suspend fun updateUser(user: User) = userDataBaseDao.update(user)

    suspend fun deleteUser(user: User) = userDataBaseDao.delete(user)

    fun getAllUser(): Flow<List<User>> = userDataBaseDao
        .getUser()
        .flowOn(Dispatchers.IO)
        .conflate()

    fun getCronById(id: Long): Flow<User> = userDataBaseDao
        .getUserById(id)
        .flowOn(Dispatchers.IO)
        .conflate()
}