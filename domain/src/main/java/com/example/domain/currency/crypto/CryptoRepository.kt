package com.example.domain.currency.crypto

import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    suspend fun getCryptoList(): Flow<List<Crypto>>

    suspend fun insertCryptoList(): Boolean

    suspend fun remoteAllCrypto(): Boolean

}