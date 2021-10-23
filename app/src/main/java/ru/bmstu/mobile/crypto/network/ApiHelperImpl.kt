package ru.bmstu.mobile.crypto.network

import com.skydoves.sandwich.ApiResponse
import ru.bmstu.mobile.crypto.model.CryptoResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getHistory(
        from: String,
        to: String,
        limit: String,
        aggregate: Int,
    ): ApiResponse<CryptoResponse> {
        return apiService.getHistory(
            from, to, limit, aggregate
        )
    }
}
