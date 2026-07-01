package com.example.myapplication1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DonationViewModel : ViewModel() {
    var amount by mutableStateOf("")
}
