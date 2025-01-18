package com.example.domain

import com.example.domain.currency.fiat.FiatRepository
import com.example.domain.currency.fiat.GetFiatListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetFiatListUseCaseTest {

    private val repository = mockk<FiatRepository>()

    @Test
    fun getFiatList() = runTest {

        val testData = TestData()

        coEvery { repository.getFiatList() } returns flow {
            emit(testData.fiatList)
        }

        GetFiatListUseCase(repository).invoke().collect {
            Assert.assertEquals(testData.fiatList, it)
        }
    }


}