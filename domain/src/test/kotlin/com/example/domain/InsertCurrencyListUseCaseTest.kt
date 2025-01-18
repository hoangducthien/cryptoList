package com.example.domain

import com.example.domain.currency.InsertCurrencyListUseCase
import com.example.domain.currency.crypto.CryptoRepository
import com.example.domain.currency.fiat.FiatRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class InsertCurrencyListUseCaseTest {

    private val fiatRepository = mockk<FiatRepository>()
    private val cryptoRepository = mockk<CryptoRepository>()

    @Test
    fun getFiatList() = runTest {

        coEvery { fiatRepository.insertFiatList() } returns true
        coEvery { cryptoRepository.insertCryptoList() } returns true

        Assert.assertEquals(true, InsertCurrencyListUseCase(cryptoRepository, fiatRepository).invoke())
    }


}