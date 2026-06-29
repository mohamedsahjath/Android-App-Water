package com.example.myapplication1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WaterUsage::class, User::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun waterUsageDao(): WaterUsageDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "water_db"
                    )
                        .fallbackToDestructiveMigration(dropAllTables = true)
                        .build()

                INSTANCE = instance

                instance
            }
        }
    }
}