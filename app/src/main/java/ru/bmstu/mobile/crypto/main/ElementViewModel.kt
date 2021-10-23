package ru.bmstu.mobile.crypto.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.bmstu.mobile.crypto.model.DataX
import javax.inject.Inject

@HiltViewModel
class ElementViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    lateinit var cryptoCurrency: String
    lateinit var realCurrency: String
    lateinit var data: List<DataX>


    fun init() {
        this.cryptoCurrency = repository.getCryptoCurrencyType()
        this.realCurrency = repository.getRealCurrencyType()
        viewModelScope.launch {
            val crypto = repository.getCurrency()
            crypto.suspendOnSuccess {
                delay(1000)
                this@ElementViewModel.data = this.data.data.data
            }.suspendOnException {
                Log.e("VM", "${this.message}")
            }.suspendOnError {
                Log.e("VM", "${this.statusCode}")
            }
        }
    }
}