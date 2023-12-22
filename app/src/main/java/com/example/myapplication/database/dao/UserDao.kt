package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("select * from users")
    fun getAll(): Flow<List<User>>

    @Query("select * from users where users.id = :id")
    fun getById(id: Int): Flow<User?>

    @Query("select * from users where users.login = :login")
    suspend fun getByLogin(login: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    fun getUserByLoginAndPassword(login: String, password: String): User?
}