package com.example.myapplication

import android.content.Context
import com.example.myapplication.database.MobileAppDataBase
import com.example.myapplication.database.repositories.CardRepository
import com.example.myapplication.database.repositories.OfflineCardRepository
import com.example.myapplication.database.repositories.OfflineUserRepository
import com.example.myapplication.database.repositories.UserRepository

interface MobileAppContainer {
    val cardRepository: CardRepository
    val userRepository: UserRepository
}

class MobileAppDataContainer(private val context: Context): MobileAppContainer {
    override val cardRepository: CardRepository by lazy {
        OfflineCardRepository(MobileAppDataBase.getInstance(context).cardDao())
    }

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(MobileAppDataBase.getInstance(context).userDao())
    }

    companion object{
        const val TIMEOUT = 5000L
    }
}