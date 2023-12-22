package com.example.myapplication.database.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.database.dao.CardDao
import com.example.myapplication.database.entities.Card
import kotlinx.coroutines.flow.Flow

class OfflineCardRepository(private val cardDao: CardDao): CardRepository {
    override fun getAllCards(): Flow<PagingData<Card>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10,
                maxSize = 15
            ),
            pagingSourceFactory = {
                cardDao.getAll()
            }
        ).flow
    }

    override fun getCardsByUserId(UserId: Int):Flow<PagingData<Card>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 1,
                enablePlaceholders = true,
                initialLoadSize = 10,
                maxSize = 15
            ),
            pagingSourceFactory = {
                cardDao.getByUserId(UserId)
            }
        ).flow
    }

    override fun getCardById(id: Int): Flow<Card?> = cardDao.getById(id)

    override suspend fun insertCard(card: Card) = cardDao.insert(card)

    override suspend fun updateCard(card: Card) = cardDao.update(card)

    override suspend fun deleteCard(card: Card) = cardDao.delete(card)

}