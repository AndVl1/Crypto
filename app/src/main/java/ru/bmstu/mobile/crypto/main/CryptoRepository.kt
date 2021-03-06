package ru.bmstu.mobile.crypto.main

import javax.inject.Inject
import ru.bmstu.mobile.crypto.di.AppPreferences
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.network.ApiHelper
import ru.bmstu.mobile.crypto.network.Repository

class CryptoRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val preferences: AppPreferences,
) : Repository {

    suspend fun getCurrency() = apiHelper.getHistory(
        from = getCryptoCurrencyType(),
        to = getRealCurrencyType(),
        limit = "20",
        aggregate = preferences.getInt("DAYS", default = 30)
    )

    fun updateCurrency(currency: CryptoCurrency) {
        preferences.putString(CurrencyType.CRYPTO, currency.name)
    }

    fun getCryptoCurrencyType() = preferences.getNullableString(CurrencyType.CRYPTO) ?: "BTC"

    fun getRealCurrencyType() = preferences.getNullableString(CurrencyType.REAL) ?: "USD"
}
