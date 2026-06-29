package com.example.myapplication1.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_usage")
data class WaterUsage(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    val date:String,

    val amount:String,

    val description:String
)