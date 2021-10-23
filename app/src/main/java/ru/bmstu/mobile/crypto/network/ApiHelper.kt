package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import ru.bmstu.mobile.crypto.model.CryptoResponse

interface ApiHelper {

    suspend fun getHistory(fsym: String, tsym: String): ApiResponse<CryptoResponse>
}
