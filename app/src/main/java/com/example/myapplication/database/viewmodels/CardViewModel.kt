package com.example.myapplication.database.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.database.dao.CardDao
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CardViewModel(private val cardRepository : CardRepository):ViewModel() {
    val getAllCards: Flow<PagingData<Card>> = cardRepository.getAllCards().cachedIn(viewModelScope)

    fun getCardById(id: Int): Flow<Card?> = cardRepository.getCardById(id)

    fun getCardByUserId(userId: Int): Flow<PagingData<Card>> = cardRepository.getCardsByUserId(userId).cachedIn(viewModelScope)

    fun insertCard(card: Card) = viewModelScope.launch { cardRepository.insertCard(card) }

    fun updateCard(card: Card) = viewModelScope.launch { cardRepository.updateCard(card) }

    fun deleteCard(card: Card) = viewModelScope.launch { cardRepository.deleteCard(card) }
}