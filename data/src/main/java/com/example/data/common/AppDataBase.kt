package com.example.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.currency.crypto.CryptoDao
import com.example.data.currency.crypto.CryptoEntity
import com.example.data.currency.fiat.FiatDao
import com.example.data.currency.fiat.FiatEntity

@Database(entities = [FiatEntity::class, CryptoEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun fiatDao(): FiatDao
    abstract fun cryptoDao(): CryptoDao
}