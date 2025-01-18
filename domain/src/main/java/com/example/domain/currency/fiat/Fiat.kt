package com.example.domain.currency.fiat

import com.example.domain.currency.Currency
import com.google.gson.annotations.SerializedName

class Fiat(
    id: String,
    name: String,
    symbol: String,
    @SerializedName("code") val code: String
) : Currency(id, name, symbol)