package com.example.cryptolist.common

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    companion object {
        const val STATE_NORMAL = 1
        const val STATE_LOADING = 2
    }

    // keep last event until there is subscriber consume
    protected val _errorDataFlow = MutableSharedFlow<Throwable>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val errorDataFlow: SharedFlow<Throwable> = _errorDataFlow

    private val _uiStateFlow = MutableStateFlow(STATE_NORMAL)
    val uiStateFlow: StateFlow<Int> = _uiStateFlow

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handlingException(exception)
    }

    // when error occur, hide loading, emit exception to UI
    protected open fun handlingException(exception: Throwable) {
        updateUIState(STATE_NORMAL)
        _errorDataFlow.tryEmit(exception)
    }

    protected fun updateUIState(uiState: Int) {
        _uiStateFlow.value = uiState
    }

}