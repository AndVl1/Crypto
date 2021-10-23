package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bmstu.mobile.crypto.model.CryptoResponse

interface ApiService {

    @GET("/data/v2/histoday")
    suspend fun getHistory(
        @Query("fsym")
        from: String = "BTC",
        @Query("tsym")
        to: String = "USD",
        @Query("limit")
        limit: String = "10",
        @Query("aggregate")
        aggregate: Int? = null,
    ): ApiResponse<CryptoResponse>
}
