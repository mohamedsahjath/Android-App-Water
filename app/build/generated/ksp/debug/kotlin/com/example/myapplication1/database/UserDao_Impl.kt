package com.example.myapplication1.database

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUser: EntityInsertAdapter<User>
  init {
    this.__db = __db
    this.__insertAdapterOfUser = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `users` (`id`,`firstName`,`lastName`,`password`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.firstName)
        statement.bindText(3, entity.lastName)
        statement.bindText(4, entity.password)
      }
    }
  }

  public override suspend fun insertUser(user: User): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUser.insert(_connection, user)
  }

  public override suspend fun login(name: String, password: String): User? {
    val _sql: String = "SELECT * FROM users WHERE (firstName = ? OR lastName = ?) AND password = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        _argIndex = 2
        _stmt.bindText(_argIndex, name)
        _argIndex = 3
        _stmt.bindText(_argIndex, password)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          _result = User(_tmpId,_tmpFirstName,_tmpLastName,_tmpPassword)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAnyUser(): User? {
    val _sql: String = "SELECT * FROM users LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfFirstName: Int = getColumnIndexOrThrow(_stmt, "firstName")
        val _columnIndexOfLastName: Int = getColumnIndexOrThrow(_stmt, "lastName")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpFirstName: String
          _tmpFirstName = _stmt.getText(_columnIndexOfFirstName)
          val _tmpLastName: String
          _tmpLastName = _stmt.getText(_columnIndexOfLastName)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          _result = User(_tmpId,_tmpFirstName,_tmpLastName,_tmpPassword)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
