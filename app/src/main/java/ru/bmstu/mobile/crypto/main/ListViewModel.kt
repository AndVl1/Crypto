package ru.bmstu.mobile.crypto.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.bmstu.mobile.crypto.model.Data
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    private val _cryptoHistory = MutableSharedFlow<Data?>()
    val cryptoHistory = _cryptoHistory.asSharedFlow()

    fun init(fsym: String, tsym: String) {
        viewModelScope.launch {
            val crypto = repository.getCurrency(fsym, tsym)

            crypto.suspendOnSuccess {
                _cryptoHistory.emit(this.data.data)
            }.suspendOnException {
                Log.e("VM", "${this.message}")
            }.suspendOnError {
                Log.e("VM", "${this.statusCode}")
            }
        }
    }
}