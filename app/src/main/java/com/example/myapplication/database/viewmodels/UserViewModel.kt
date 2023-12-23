package com.example.myapplication.database.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.GlobalUser
import com.example.myapplication.database.entities.User
import com.example.myapplication.database.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    val getAllUsers = userRepository.getAllUsers()

    suspend fun getUser(id: Int): Flow<User?> = userRepository.getUserById(id)

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }

    suspend fun getUserByLogin(login: String): User? = userRepository.getUserByLogin(login)

    fun regUser(user: User) = viewModelScope.launch {
        val globalUser = userRepository.getUserByLogin(user.login)
        globalUser?.let {
            return@launch
        } ?: run {
            if(user.password.isEmpty()){
                return@launch
            }

            userRepository.insertUser(user)
            GlobalUser.getInstance().setUser(userRepository.getUserByLogin(user.login))
        }
    }

    fun authUser(user: User) = viewModelScope.launch {
        val globalUser = userRepository.getUserByLogin(user.login)
        if (user.password.isNotEmpty() && user.password == globalUser?.password){
            GlobalUser.getInstance().setUser(globalUser)
            val user123 = GlobalUser.getInstance().getUser()
            println(user123)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}