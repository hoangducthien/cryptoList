package com.example.domain

import com.example.domain.currency.crypto.CryptoRepository
import com.example.domain.currency.crypto.GetCryptoListUseCase
import com.example.domain.currency.fiat.FiatRepository
import com.example.domain.currency.fiat.GetFiatListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCryptoListUseCaseTest {

    private val repository = mockk<CryptoRepository>()

    @Test
    fun getCryptoList() = runTest {

        val testData = TestData()

        coEvery { repository.getCryptoList() } returns flow {
            emit(testData.cryptoList)
        }

        GetCryptoListUseCase(repository).invoke().collect {
            Assert.assertEquals(testData.cryptoList, it)
        }
    }


}