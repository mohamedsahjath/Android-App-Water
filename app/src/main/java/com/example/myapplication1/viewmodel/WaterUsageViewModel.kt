package com.example.myapplication1.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.myapplication1.database.AppDatabase
import com.example.myapplication1.database.WaterUsage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WaterUsageViewModel(
    application: Application
) : AndroidViewModel(application) {

    var date by mutableStateOf("")
    var amount by mutableStateOf("")
    var description by mutableStateOf("")

    private val dao =
        AppDatabase.getDatabase(application)
            .waterUsageDao()

    val usageList =
        dao.getAllUsage()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                emptyList()
            )

    fun addUsage(
        date: String,
        amount: String,
        description: String
    ) {


        viewModelScope.launch {

            dao.insertUsage(
                WaterUsage(
                    date = date,
                    amount = amount,
                    description = description
                )
            )
        }
    }

    fun deleteUsage(
        usage: WaterUsage
    ) {


        viewModelScope.launch {

            dao.deleteUsage(usage)
        }
    }

    fun resetFields() {
        date = ""
        amount = ""
        description = ""
    }

    fun deleteAllUsage() {
        viewModelScope.launch {
            dao.deleteAllUsage()
        }
    }
}
