package com.example.data.currency.fiat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.currency.CurrencyEntity
import com.example.domain.currency.fiat.Fiat

@Entity
class FiatEntity(
    @PrimaryKey override val id: String, name: String, symbol: String, val code: String
) : CurrencyEntity(id, name, symbol)

fun FiatEntity.toDomainModel(): Fiat {
    return Fiat(id, name, symbol, code)
}

fun Fiat.toDbModel(): FiatEntity {
    return FiatEntity(id, name, symbol, code)
}

