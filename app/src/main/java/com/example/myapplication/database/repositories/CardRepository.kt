package com.example.myapplication.database.repositories

import androidx.paging.PagingData
import com.example.myapplication.database.entities.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getAllCards(): Flow<PagingData<Card>>

    fun getCardsByUserId(userId: Int): Flow<PagingData<Card>>

    fun getCardById(id: Int):  Flow<Card?>

    suspend fun insertCard(card: Card)

    suspend fun updateCard(card: Card)

    suspend fun deleteCard(card: Card)
}