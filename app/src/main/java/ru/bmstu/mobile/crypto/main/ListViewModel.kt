package ru.bmstu.mobile.crypto.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.network.LoadingState
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _cryptoHistory = MutableSharedFlow<LoadingState?>()
    val cryptoHistory = _cryptoHistory.asSharedFlow()
    private val _currency = MutableStateFlow(CryptoCurrency.valueOf(repository.getCryptoCurrencyType()))
    val currency = _currency.asStateFlow()

    fun init() {
        viewModelScope.launch {
            val crypto = repository.getCurrency()
            _currency.emit(CryptoCurrency.valueOf(repository.getCryptoCurrencyType()))
            _cryptoHistory.emit(LoadingState.Loading)
            crypto.suspendOnSuccess {
                delay(1000)
                _cryptoHistory.emit(LoadingState.Loaded(this.data.data))
            }.suspendOnException {
                _cryptoHistory.emit(LoadingState.Error)
                Log.e("VM", "${this.message}")
            }.suspendOnError {
                _cryptoHistory.emit(LoadingState.Error)
                Log.e("VM", "${this.statusCode}")
            }
        }
    }

    fun update(currency: CryptoCurrency) {
        viewModelScope.launch {
            _cryptoHistory.emit(LoadingState.Loading)
            repository.updateCurrency(currency)
            delay(1000)
            val crypto = repository.getCurrency()
            crypto.suspendOnSuccess {
                _cryptoHistory.emit(LoadingState.Loaded(this.data.data))
            }.suspendOnException {
                _cryptoHistory.emit(LoadingState.Error)
                Log.e("VM", "${this.message}")
            }.suspendOnError {
                _cryptoHistory.emit(LoadingState.Error)
                Log.e("VM", "${this.statusCode}")
            }
        }
    }
}