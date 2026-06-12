package com.example.myapplication1.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class WaterUsageDao_Impl(
  __db: RoomDatabase,
) : WaterUsageDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfWaterUsage: EntityInsertAdapter<WaterUsage>

  private val __deleteAdapterOfWaterUsage: EntityDeleteOrUpdateAdapter<WaterUsage>

  private val __updateAdapterOfWaterUsage: EntityDeleteOrUpdateAdapter<WaterUsage>
  init {
    this.__db = __db
    this.__insertAdapterOfWaterUsage = object : EntityInsertAdapter<WaterUsage>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `water_usage` (`id`,`date`,`amount`,`description`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: WaterUsage) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.date)
        statement.bindText(3, entity.amount)
        statement.bindText(4, entity.description)
      }
    }
    this.__deleteAdapterOfWaterUsage = object : EntityDeleteOrUpdateAdapter<WaterUsage>() {
      protected override fun createQuery(): String = "DELETE FROM `water_usage` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WaterUsage) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfWaterUsage = object : EntityDeleteOrUpdateAdapter<WaterUsage>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `water_usage` SET `id` = ?,`date` = ?,`amount` = ?,`description` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: WaterUsage) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.date)
        statement.bindText(3, entity.amount)
        statement.bindText(4, entity.description)
        statement.bindLong(5, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertUsage(usage: WaterUsage): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfWaterUsage.insert(_connection, usage)
  }

  public override suspend fun deleteUsage(usage: WaterUsage): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfWaterUsage.handle(_connection, usage)
  }

  public override suspend fun updateUsage(usage: WaterUsage): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfWaterUsage.handle(_connection, usage)
  }

  public override fun getAllUsage(): Flow<List<WaterUsage>> {
    val _sql: String = "SELECT * FROM water_usage"
    return createFlow(__db, false, arrayOf("water_usage")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<WaterUsage> = mutableListOf()
        while (_stmt.step()) {
          val _item: WaterUsage
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpDate: String
          _tmpDate = _stmt.getText(_columnIndexOfDate)
          val _tmpAmount: String
          _tmpAmount = _stmt.getText(_columnIndexOfAmount)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          _item = WaterUsage(_tmpId,_tmpDate,_tmpAmount,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllUsage() {
    val _sql: String = "DELETE FROM water_usage"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
