package com.example.myapplication.database.repositories

import com.example.myapplication.database.dao.UserDao
import com.example.myapplication.database.entities.User
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao): UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAll()

    override suspend fun getUserById(id: Int): User? = userDao.getById(id)

    override suspend fun getUserByLogin(login: String): User? = userDao.getByLogin(login)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun updateUser(user: User) = userDao.update(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun getUserByLoginAndPassword(login: String, password: String): User? = userDao.getUserByLoginAndPassword(login, password)
}