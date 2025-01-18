package com.example.domain

import com.example.domain.currency.RemoveAllCurrencyUseCase
import com.example.domain.currency.crypto.CryptoRepository
import com.example.domain.currency.fiat.FiatRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class RemoveAllCurrencyListUseCaseTest {

    private val fiatRepository = mockk<FiatRepository>()
    private val cryptoRepository = mockk<CryptoRepository>()

    @Test
    fun getFiatList() = runTest {

        coEvery { fiatRepository.remoteAllFiat() } returns true
        coEvery { cryptoRepository.remoteAllCrypto() } returns true

        Assert.assertEquals(true, RemoveAllCurrencyUseCase(cryptoRepository, fiatRepository).invoke())
    }


}