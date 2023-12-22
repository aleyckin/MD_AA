package com.example.myapplication.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.database.entities.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("select * from cards")
    fun getAll(): PagingSource<Int, Card>

    @Query("select * from cards where cards.id = :id")
    fun getById(id: Int): Flow<Card?>

    @Query("select * from cards where cards.user_id = :userId")
    fun getByUserId(userId: Int): PagingSource<Int, Card>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)

    @Update
    suspend fun update(card: Card)

    @Delete
    suspend fun delete(card: Card)
}