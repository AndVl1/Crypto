package ru.bmstu.mobile.crypto.main

import ru.bmstu.mobile.crypto.di.AppPreferences
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.network.ApiHelper
import ru.bmstu.mobile.crypto.network.Repository
import javax.inject.Inject


class CryptoRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val preferences: AppPreferences,
) : Repository {

    suspend fun getCurrency() = apiHelper.getHistory(
        from = getCryptoCurrencyType(),
        to = getRealCurrencyType(),
        limit = "10",
        aggregate = 1
    )

    fun updateCurrency(currency: CryptoCurrency) {
        preferences.putString(CurrencyType.CRYPTO, currency.name)
    }

    fun getCryptoCurrencyType() = preferences.getString(CurrencyType.CRYPTO)

    fun getRealCurrencyType() = preferences.getNullableString(CurrencyType.REAL) ?: "USD"
}
