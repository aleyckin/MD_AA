package com.example.myapplication.database.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.MobileApp

object MobileAppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CardViewModel(app().container.cardRepository)
        }
        initializer {
            UserViewModel(app().container.userRepository)
        }
    }
}

fun CreationExtras.app(): MobileApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MobileApp)