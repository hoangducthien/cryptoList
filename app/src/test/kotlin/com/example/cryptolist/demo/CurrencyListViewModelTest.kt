package com.example.cryptolist.demo

import com.example.cryptolist.common.TestData
import com.example.cryptolist.common.BaseViewModel
import com.example.cryptolist.common.setupDispatcher
import com.example.cryptolist.currency.CurrencyListViewModel
import com.example.cryptolist.currency.TYPE_ALL
import com.example.cryptolist.currency.TYPE_CRYPTO
import com.example.cryptolist.currency.TYPE_FIAT
import com.example.domain.currency.GetCurrencyListUseCase
import com.example.domain.currency.crypto.GetCryptoListUseCase
import com.example.domain.currency.fiat.GetFiatListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CurrencyListViewModelTest {

    private val getFiatListUseCase = mockk<GetFiatListUseCase>()
    private val getCryptoListUseCase = mockk<GetCryptoListUseCase>()
    private val getCurrencyListUseCase = GetCurrencyListUseCase(getCryptoListUseCase, getFiatListUseCase)
    private val viewModel = CurrencyListViewModel(getFiatListUseCase, getCryptoListUseCase, getCurrencyListUseCase)

    @Test
    fun testGetFiatList() = runTest {
        val testData = TestData()
        setupDispatcher()
        coEvery { getFiatListUseCase() } returns flow {
            emit(testData.fiatList)
        }
        viewModel.getCurrencyList(TYPE_FIAT)
        Assert.assertEquals(testData.fiatList.first().id, viewModel.currencyListFlow.value.first().id)
        Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
    }

    @Test
    fun testGetFiatListFailed() = runTest {
        setupDispatcher()
        launch(NonCancellable + viewModel.coroutineExceptionHandler) {
            val errorMessage = "get fiat list failed"
            coEvery { getFiatListUseCase() }.throws(Exception(errorMessage))
            viewModel.getCurrencyList(TYPE_FIAT)
            Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
            Assert.assertEquals(errorMessage, viewModel.errorDataFlow.first().message)
        }
    }

    @Test
    fun testGetCryptoList() = runTest {
        val testData = TestData()
        setupDispatcher()
        coEvery { getCryptoListUseCase() } returns flow {
            emit(testData.cryptoList)
        }
        viewModel.getCurrencyList(TYPE_CRYPTO)
        Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
        Assert.assertEquals(testData.cryptoList.first().id, viewModel.currencyListFlow.value.first().id)
    }

    @Test
    fun testGetCryptoListFailed() = runTest {
        setupDispatcher()
        launch(NonCancellable + viewModel.coroutineExceptionHandler) {
            val errorMessage = "get crypto list failed"
            coEvery { getCryptoListUseCase() }.throws(Exception(errorMessage))
            viewModel.getCurrencyList(TYPE_CRYPTO)
            Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
            Assert.assertEquals(errorMessage, viewModel.errorDataFlow.first().message)
        }
    }

    @Test
    fun testGetAllList() = runTest {
        val testData = TestData()
        setupDispatcher()
        coEvery { getCryptoListUseCase() } returns flow {
            emit(testData.cryptoList)
        }
        coEvery { getFiatListUseCase() } returns flow {
            emit(testData.fiatList)
        }
        viewModel.getCurrencyList(TYPE_ALL)
        Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
        Assert.assertEquals(testData.cryptoList.size + testData.fiatList.size, viewModel.currencyListFlow.value.size)
        Assert.assertEquals(testData.cryptoList.first().id, viewModel.currencyListFlow.value.first().id)
    }

    @Test
    fun testGetAllListFailed() = runTest {
        val testData = TestData()
        setupDispatcher()
        launch(NonCancellable + viewModel.coroutineExceptionHandler) {
            val errorMessage = "get all list failed"
            coEvery { getCryptoListUseCase() }.throws(Exception(errorMessage))
            coEvery { getFiatListUseCase() } returns flow {
                testData.fiatList
            }
            viewModel.getCurrencyList(TYPE_ALL)
            Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
            Assert.assertEquals(errorMessage, viewModel.errorDataFlow.first().message)
        }
    }

    @Test
    fun testSearch() = runTest {
        val testData = TestData()
        setupDispatcher()
        coEvery { getCryptoListUseCase() } returns flow {
            emit(testData.cryptoList)
        }
        coEvery { getFiatListUseCase() } returns flow {
            emit(testData.fiatList)
        }
        viewModel.getCurrencyList(TYPE_ALL)

        var keyword = "classic"
        viewModel.search(keyword)
        Assert.assertEquals(1, viewModel.currencyListFlow.value.size)
        Assert.assertEquals(true, viewModel.currencyListFlow.value.first().name.contains(keyword, true))


        keyword = "et"
        viewModel.search(keyword)
        Assert.assertEquals(2, viewModel.currencyListFlow.value.size)
        Assert.assertEquals(true, viewModel.currencyListFlow.value[0].symbol.contains(keyword, true))
        Assert.assertEquals(true, viewModel.currencyListFlow.value[1].symbol.contains(keyword, true))
    }

}