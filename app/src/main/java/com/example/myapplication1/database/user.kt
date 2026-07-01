package com.example.myapplication1.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val password: String
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE (firstName = :name OR lastName = :name) AND password = :password LIMIT 1")
    suspend fun login(name: String, password: String): User?

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getAnyUser(): User?
}
