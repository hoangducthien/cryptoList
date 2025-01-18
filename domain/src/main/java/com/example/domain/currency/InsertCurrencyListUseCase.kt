package com.example.domain.currency

import com.example.domain.currency.crypto.CryptoRepository
import com.example.domain.currency.fiat.FiatRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class InsertCurrencyListUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val fiatRepository: FiatRepository
) {

    suspend operator fun invoke(): Boolean {
        // to run 2 query in parallel
        return coroutineScope {
            val insertCrypto = async {
                cryptoRepository.insertCryptoList()
            }

            val insertFiat = async {
                fiatRepository.insertFiatList()
            }
            insertCrypto.await() && insertFiat.await()
        }
    }
}
