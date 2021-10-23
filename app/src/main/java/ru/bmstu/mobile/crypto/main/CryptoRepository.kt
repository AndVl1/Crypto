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
        from = preferences.getNullableString(CRYPTO_CURRENCY_KEY) ?: "BTC",
        to = preferences.getNullableString(CURRENCY_KEY) ?: "USD",
        limit = "10",
        aggregate = 1
    )

    fun updateCurrency(currency: CryptoCurrency) {
        preferences.putString(CRYPTO_CURRENCY_KEY, currency.name)
    }

    companion object {
        private const val CRYPTO_CURRENCY_KEY = "crypto"
        private const val CURRENCY_KEY = "real"
    }
}
