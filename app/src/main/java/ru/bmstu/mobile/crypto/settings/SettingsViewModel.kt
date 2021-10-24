package ru.bmstu.mobile.crypto.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.bmstu.mobile.crypto.model.Currency
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    fun getCurrency(): Currency {
        return repository.getCurrency()
    }

    fun updateCurrency(currency: Currency) {
        repository.putCurrency(currency)
    }
}
