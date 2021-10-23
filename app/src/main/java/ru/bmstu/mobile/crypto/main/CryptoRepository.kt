package ru.bmstu.mobile.crypto.main

import ru.bmstu.mobile.crypto.network.ApiHelper
import ru.bmstu.mobile.crypto.network.Repository
import javax.inject.Inject


class CryptoRepository @Inject constructor(
    private val apiHelper: ApiHelper
) : Repository {

    suspend fun getCurrency(fsym: String, tsym: String) = apiHelper.getHistory(fsym, tsym)
}