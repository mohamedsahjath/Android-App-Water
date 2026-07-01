package com.example.myapplication1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.database.AppDatabase
import com.example.myapplication1.database.User
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    var currentUser by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            currentUser = userDao.getAnyUser()
        }
    }

    fun signup(firstName: String, lastName: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val user = User(firstName = firstName, lastName = lastName, password = password)
                userDao.insertUser(user)
                currentUser = user
                onResult(true)
            } catch (_: Exception) {
                onResult(false)
            }
        }
    }

    fun login(name: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.login(name, password)
            if (user != null) {
                currentUser = user
            }
            onResult(user != null)
        }
    }

    fun logout() {
        currentUser = null
    }
}
