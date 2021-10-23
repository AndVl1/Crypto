package ru.bmstu.mobile.crypto.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoResponse(
    @Json(name = "Data")
    val `data`: Data,
    @Json(name = "HasWarning")
    val hasWarning: Boolean,
    @Json(name = "Message")
    val message: String,
    @Json(name = "RateLimit")
    val rateLimit: RateLimit,
    @Json(name = "Response")
    val response: String,
    @Json(name = "Type")
    val type: Int
)