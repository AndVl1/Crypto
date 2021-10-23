package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import ru.bmstu.mobile.crypto.model.CryptoResponse

interface ApiHelper {

    suspend fun getHistory(
        from: String = "BTC",
        to: String = "USD",
        limit: String = "10",
        aggregate: Int = 1,
    ): ApiResponse<CryptoResponse>
}
