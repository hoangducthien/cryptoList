package com.example.domain.currency.crypto

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCryptoListUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {

    suspend operator fun invoke(): Flow<List<Crypto>> {
        return cryptoRepository.getCryptoList()
    }
}