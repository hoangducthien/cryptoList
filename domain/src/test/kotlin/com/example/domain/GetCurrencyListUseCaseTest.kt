package com.example.domain

import com.example.domain.currency.GetCurrencyListUseCase
import com.example.domain.currency.crypto.GetCryptoListUseCase
import com.example.domain.currency.fiat.GetFiatListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCurrencyListUseCaseTest {

    private val getFiatListUseCase = mockk<GetFiatListUseCase>()
    private val getCryptoListUseCase = mockk<GetCryptoListUseCase>()

    @Test
    fun getCurrencyList() = runTest {

        val testData = TestData()

        coEvery { getCryptoListUseCase() } returns flow {
            emit(testData.cryptoList)
        }
        coEvery { getFiatListUseCase() } returns flow {
            emit(testData.fiatList)
        }

        GetCurrencyListUseCase(getCryptoListUseCase, getFiatListUseCase).invoke().collect {
            Assert.assertEquals(testData.cryptoList + testData.fiatList, it)
        }
    }


}