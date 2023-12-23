package com.example.myapplication.database.repositories

import androidx.room.Query
import com.example.myapplication.database.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>

    suspend fun getUserById(id: Int):  Flow<User?>

    suspend fun getUserByLogin(login: String): User?

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserByLoginAndPassword(login: String, password: String): User?
}