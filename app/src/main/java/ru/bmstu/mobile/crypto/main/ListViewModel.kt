@file:Suppress("UNUSED_PARAMETER")

package ru.bmstu.mobile.crypto.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bmstu.mobile.crypto.base.IntentHandler
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.model.Currency
import ru.bmstu.mobile.crypto.network.ListScreenState

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel(), IntentHandler<LoadingEvent> {

    private val _cryptoHistory = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val cryptoHistory = _cryptoHistory.asSharedFlow()
    private val _cryptoCurrency = MutableStateFlow(CryptoCurrency.valueOf(repository.getCryptoCurrencyType()))
    val cryptoCurrency = _cryptoCurrency.asStateFlow()
    private val _currency = MutableStateFlow(Currency.valueOf(repository.getRealCurrencyType()))
    val currency = _currency.asStateFlow()

    override fun handleIntent(event: LoadingEvent) {
        when (val current = _cryptoHistory.value) {
            is ListScreenState.Loading -> reduce(event, current)
            is ListScreenState.Loaded -> reduce(event, current)
            is ListScreenState.Error -> reduce(event, current)
        }
    }

    private fun reduce(event: LoadingEvent, currentState: ListScreenState.Loading) {
        when (event) {
            is LoadingEvent.ReloadScreen -> getHistory(true, event.currency)
            is LoadingEvent.EnterScreen -> getHistory(true)
            else -> {}
        }
    }

    private fun reduce(event: LoadingEvent, currentState: ListScreenState.Loaded) {
        when (event) {
            is LoadingEvent.ReloadScreen -> getHistory(true, newCryptoCurrency = event.currency)
            else -> {}
        }
    }

    private fun reduce(event: LoadingEvent, currentState: ListScreenState.Error) {
        when (event) {
            is LoadingEvent.ReloadScreen -> getHistory(true)
            else -> {}
        }
    }

    private fun getHistory(
        needsToRefresh: Boolean = false,
        newCryptoCurrency: CryptoCurrency = this.cryptoCurrency.value
    ) {
        viewModelScope.launch {
            if (needsToRefresh) {
                _cryptoHistory.emit(ListScreenState.Loading)
            }

            _cryptoCurrency.emit(newCryptoCurrency)
            repository.updateCurrency(newCryptoCurrency)
            val crypto = repository.getCurrency()
            crypto.suspendOnSuccess {
                _cryptoHistory.emit(ListScreenState.Loaded(this.data.values))
            }.suspendOnException {
                _cryptoHistory.emit(ListScreenState.Error)
                Log.e("VM", "${this.message}")
            }.suspendOnError {
                _cryptoHistory.emit(ListScreenState.Error)
                Log.e("VM", "${this.statusCode}")
            }
        }
    }
}
