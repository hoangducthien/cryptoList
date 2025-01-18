package com.example.cryptolist.currency

import com.example.domain.currency.Currency
import com.example.domain.currency.crypto.Crypto
import com.example.domain.currency.fiat.Fiat


const val TYPE_FIAT = 2
const val TYPE_CRYPTO = 1
const val TYPE_ALL = 0

data class UICurrency(
    val id: String,
    val name: String,
    val symbol: String,
    val code: String,
    val displayCode: String
)

fun Currency.toUIModel(): UICurrency {
    val code = if (this is Fiat) code else ""
    val displayCode = if (this is Crypto) symbol else ""
    return UICurrency(id, name, symbol, code, displayCode)
}