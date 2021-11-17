package ru.bmstu.mobile.crypto.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import ru.bmstu.mobile.crypto.model.Currency

// TODO replace with concurrent
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

    fun updateDays(days: Int) {
        repository.putDays(days)
    }

    fun getDays(): Int {
        return repository.getDays()
    }
}
