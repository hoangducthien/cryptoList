package com.example.cryptolist.currency

import androidx.lifecycle.viewModelScope
import com.example.cryptolist.common.BaseViewModel
import com.example.domain.currency.GetCurrencyListUseCase
import com.example.domain.currency.crypto.GetCryptoListUseCase
import com.example.domain.currency.fiat.GetFiatListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getFiatListUseCase: GetFiatListUseCase,
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : BaseViewModel() {

    private val _currencyListFlow = MutableStateFlow<List<UICurrency>>(emptyList())
    val currencyListFlow: StateFlow<List<UICurrency>> = _currencyListFlow

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private var searchKeyword: String = ""
    private var cachedData: List<UICurrency> = emptyList()

    fun getCurrencyList(dataType: Int) {
        when (dataType) {
            TYPE_FIAT -> getFiatList()
            TYPE_CRYPTO -> getCryptoList()
            else -> getAllList()
        }
    }

    private fun getFiatList() {
        updateUIState(STATE_LOADING)
        viewModelScope.launch(coroutineExceptionHandler) {
            getFiatListUseCase.invoke().collect { fiats ->
                cachedData = fiats.map {
                    it.toUIModel()
                }
                filterDataByKeyword()
                updateUIState(STATE_NORMAL)
            }
        }
    }

    private fun getCryptoList() {
        updateUIState(STATE_LOADING)
        viewModelScope.launch(coroutineExceptionHandler) {
            getCryptoListUseCase.invoke().collect { cryptos ->
                cachedData = cryptos.map {
                    it.toUIModel()
                }
                filterDataByKeyword()
                updateUIState(STATE_NORMAL)
            }
        }
    }

    private fun getAllList() {
        updateUIState(STATE_LOADING)
        viewModelScope.launch(coroutineExceptionHandler) {
            getCurrencyListUseCase.invoke().collect { currencies ->
                cachedData = currencies.map {
                    it.toUIModel()
                }
                filterDataByKeyword()
                updateUIState(STATE_NORMAL)
            }
        }
    }

    fun search(keyword: String) {
        searchKeyword = keyword
        _isSearching.value = searchKeyword.isNotEmpty()
        filterDataByKeyword()
    }

    private fun filterDataByKeyword() {
        _currencyListFlow.value = cachedData.filter {
            it.name.startsWith(searchKeyword, true) || it.symbol.startsWith(
                searchKeyword,
                true
            ) || it.name.contains(" $searchKeyword", true)
        }
    }

}