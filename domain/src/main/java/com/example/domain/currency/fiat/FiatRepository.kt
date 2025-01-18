package com.example.domain.currency.fiat

import kotlinx.coroutines.flow.Flow

interface FiatRepository {

    suspend fun getFiatList(): Flow<List<Fiat>>

    suspend fun insertFiatList(): Boolean

    suspend fun remoteAllFiat(): Boolean

}
