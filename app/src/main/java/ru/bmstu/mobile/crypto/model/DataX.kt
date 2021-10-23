package ru.bmstu.mobile.crypto.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "close")
    val close: Double,
    @Json(name = "conversionSymbol")
    val conversionSymbol: String,
    @Json(name = "conversionType")
    val conversionType: String,
    @Json(name = "high")
    val high: Double,
    @Json(name = "low")
    val low: Double,
    @Json(name = "open")
    val `open`: Double,
    @Json(name = "time")
    val time: Long,
    @Json(name = "volumefrom")
    val volumefrom: Double,
    @Json(name = "volumeto")
    val volumeto: Double
) : Serializable
