package com.example.domain.currency

import com.google.gson.annotations.SerializedName

open class Currency(
    @SerializedName("id")  val id: String,
    @SerializedName("name")  val name: String,
    @SerializedName("symbol") val symbol: String
)