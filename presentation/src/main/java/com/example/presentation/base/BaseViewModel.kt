package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*abstract class BaseViewModel : ViewModel() {

    // Loading state tracking
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // Error state tracking
    private val _error = MutableSharedFlow<Throwable>()
    val error: SharedFlow<Throwable> = _error.asSharedFlow()

    // Function to handle loading and errors during API calls
    protected fun <T> launchWithState(
        block: suspend () -> T,
        onSuccess: suspend (T) -> Unit,
        onError: (Throwable) -> Unit = { handleError(it) }
    ) {
        viewModelScope.launch {
            _loading.emit(true)
            try {
                val result = block()
                onSuccess(result)
            } catch (e: Throwable) {
                onError(e)
            } finally {
                _loading.emit(false)
            }
        }
    }

    protected fun handleError(error: Throwable) {
        viewModelScope.launch {
            _error.emit(error)
        }
    }
}*/
abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Throwable>()
    val error: SharedFlow<Throwable> = _error.asSharedFlow()

    protected fun <T> launchWithState(
        block: suspend () -> T,
        onSuccess: suspend (T) -> Unit,
        onError: (Throwable) -> Unit = { handleError(it) },
        showLoading: Boolean = true
    ) {
        viewModelScope.launch {
            if (showLoading) _loading.emit(true) // Show loading if required
            try {
                val result = block()
                onSuccess(result)
            } catch (e: Throwable) {
                onError(e)
            } finally {
                if (showLoading) _loading.emit(false)
            }
        }
    }

    protected fun handleError(error: Throwable) {
        viewModelScope.launch {
            _error.emit(error)
        }
    }
    protected fun emitError(message: Throwable) {
        viewModelScope.launch {
            _error.emit(message)
        }
    }
}




