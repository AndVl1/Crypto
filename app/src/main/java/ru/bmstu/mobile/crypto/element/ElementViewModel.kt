package ru.bmstu.mobile.crypto.element

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bmstu.mobile.crypto.main.CryptoRepository
import javax.inject.Inject

@HiltViewModel
class ElementViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _cryptoCurrency = MutableStateFlow<String?>(null)
    val cryptoCurrency = _cryptoCurrency.asStateFlow()
    private val _realCurrency = MutableStateFlow<String?>(null)
    val realCurrency = _realCurrency.asStateFlow()

    fun init() {
        viewModelScope.launch {
            _cryptoCurrency.emit(repository.getCryptoCurrencyType())
            _realCurrency.emit(repository.getRealCurrencyType())
        }
    }
}