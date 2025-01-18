package com.example.data.currency.fiat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FiatDao {
    @Query("SELECT * FROM FiatEntity")
    fun getFiatList(): Flow<List<FiatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFiat(fiats: List<FiatEntity>)

    @Query("DELETE FROM FiatEntity")
    fun deleteAll()
}