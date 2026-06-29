package com.example.myapplication1.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ReportViewModel : ViewModel() {
    var locationAddress by mutableStateOf("")
    var problemType by mutableStateOf("")
    var description by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)

    fun updateReport(location: String, type: String, desc: String, uri: Uri?) {
        locationAddress = location
        problemType = type
        description = desc
        imageUri = uri
    }
}
