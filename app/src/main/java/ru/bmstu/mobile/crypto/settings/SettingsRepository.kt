package ru.bmstu.mobile.crypto.settings

import ru.bmstu.mobile.crypto.di.AppPreferences
import ru.bmstu.mobile.crypto.main.CurrencyType
import ru.bmstu.mobile.crypto.model.Currency
import ru.bmstu.mobile.crypto.network.Repository
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val preferences: AppPreferences,
) : Repository {

    fun putCurrency(currency: Currency) {
        preferences.putString(CurrencyType.REAL, currency.name)
    }

    fun getCurrency(): Currency {
        return preferences.getNullableString(CurrencyType.REAL)?.let {
            Currency.valueOf(it)
        } ?: Currency.USD
    }
}
