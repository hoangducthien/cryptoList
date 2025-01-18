package com.example.domain.currency

import com.example.domain.currency.crypto.GetCryptoListUseCase
import com.example.domain.currency.fiat.GetFiatListUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@JvmSuppressWildcards
class GetCurrencyListUseCase @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getFiatListUseCase: GetFiatListUseCase,
) {

    suspend operator fun invoke(): Flow<List<Currency>> {
        // to run 2 query in parallel
        return coroutineScope {
            val cryptoList = async { getCryptoListUseCase.invoke() }
            val fiatList = async { getFiatListUseCase.invoke() }
            combine(
                cryptoList.await(),
                fiatList.await()
            ) { cryptos, fiats ->
                cryptos + fiats
            }
        }
    }
}
