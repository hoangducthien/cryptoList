package com.example.cryptolist.demo

import androidx.lifecycle.viewModelScope
import com.example.cryptolist.common.BaseViewModel
import com.example.domain.currency.InsertCurrencyListUseCase
import com.example.domain.currency.RemoveAllCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val insertCurrencyListUseCase: InsertCurrencyListUseCase,
    private val removeAllCurrencyUseCase: RemoveAllCurrencyUseCase
) : BaseViewModel() {

    fun insertData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            insertCurrencyListUseCase.invoke()
        }
    }

    fun clearData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            removeAllCurrencyUseCase.invoke()
        }
    }

}