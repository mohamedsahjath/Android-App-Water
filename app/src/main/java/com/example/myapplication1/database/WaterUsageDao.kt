package com.example.myapplication1.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterUsageDao {

    @Insert
    suspend fun insertUsage(
        usage: WaterUsage
    )

    @Update
    suspend fun updateUsage(
        usage: WaterUsage
    )

    @Delete
    suspend fun deleteUsage(
        usage: WaterUsage
    )

    @Query("SELECT * FROM water_usage")
    fun getAllUsage(): Flow<List<WaterUsage>>

    @Query("DELETE FROM water_usage")
    suspend fun deleteAllUsage()
}