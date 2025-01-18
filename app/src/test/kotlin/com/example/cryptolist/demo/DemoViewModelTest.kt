package com.example.cryptolist.demo

import com.example.cryptolist.common.BaseViewModel
import com.example.cryptolist.common.setupDispatcher
import com.example.domain.currency.InsertCurrencyListUseCase
import com.example.domain.currency.RemoveAllCurrencyUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DemoViewModelTest {

    private val insertCurrencyListUseCase = mockk<InsertCurrencyListUseCase>()
    private val removeAllCurrencyUseCase = mockk<RemoveAllCurrencyUseCase>()
    private val viewModel = DemoViewModel(insertCurrencyListUseCase, removeAllCurrencyUseCase)

    @Test
    fun testInsertData() = runTest {
        setupDispatcher()
        coEvery { insertCurrencyListUseCase() } returns true
        viewModel.insertData()
        Assert.assertEquals(0, viewModel.errorDataFlow.replayCache.size)
    }

    @Test
    fun testInsertDataFailed() = runTest {
        setupDispatcher()
        launch(NonCancellable + viewModel.coroutineExceptionHandler) {
            val errorMessage = "test message"
            coEvery { insertCurrencyListUseCase() }.throws(Exception(errorMessage))
            viewModel.insertData()
            Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
            Assert.assertEquals(1, viewModel.errorDataFlow.replayCache.size)
        }
    }


    @Test
    fun testDeleteData() = runTest {
        setupDispatcher()
        coEvery { removeAllCurrencyUseCase() } returns true
        viewModel.clearData()
        Assert.assertEquals(0, viewModel.errorDataFlow.replayCache.size)
    }

    @Test
    fun testDeleteDataFailed() = runTest {
        setupDispatcher()
        launch(NonCancellable + viewModel.coroutineExceptionHandler) {
            val errorMessage = "test message"
            coEvery { removeAllCurrencyUseCase() }.throws(Exception(errorMessage))
            viewModel.clearData()
            Assert.assertEquals(BaseViewModel.STATE_NORMAL, viewModel.uiStateFlow.value)
            Assert.assertEquals(errorMessage, viewModel.errorDataFlow.first().message)
        }
    }

}