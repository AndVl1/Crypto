package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bmstu.mobile.crypto.model.CryptoResponse

interface ApiService {

    @GET("/data/v2/histoday")
    suspend fun getHistory(
        @Query("fsym")
        country: String = "BTC",
        @Query("tsym")
        category: String = "USD",
        @Query("limit")
        size: String = "10",
        @Query("aggregate")
        aggregate: Int = 1,
    ): ApiResponse<CryptoResponse>
}
