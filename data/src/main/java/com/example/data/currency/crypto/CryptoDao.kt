package com.example.data.currency.crypto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {
    @Query("SELECT * FROM CryptoEntity")
    fun getCryptoList(): Flow<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptos(cryptos: List<CryptoEntity>)

    @Query("DELETE FROM CryptoEntity")
    fun deleteAll()

}