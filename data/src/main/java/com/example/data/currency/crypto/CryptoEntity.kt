package com.example.data.currency.crypto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.currency.CurrencyEntity
import com.example.domain.currency.crypto.Crypto

@Entity
class CryptoEntity(
    @PrimaryKey override val id: String, name: String, symbol: String
) : CurrencyEntity(id, name, symbol)

fun CryptoEntity.toDomainModel(): Crypto {
    return Crypto(id, name, symbol)
}

fun Crypto.toDbModel(): CryptoEntity {
    return CryptoEntity(id, name, symbol)
}

