package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bmstu.mobile.crypto.model.CryptoResponse

interface ApiService {

    @GET("/data/v2/histoday")
    suspend fun getHistory(
        @Query("fsym")
        fsym: String = "BTC",
        @Query("tsym")
        tsym: String = "USD",
        @Query("limit")
        size: String = "10"
    ): ApiResponse<CryptoResponse>
}
