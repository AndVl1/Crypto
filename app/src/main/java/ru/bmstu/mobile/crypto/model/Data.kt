package ru.bmstu.mobile.crypto.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "Aggregated")
    val aggregated: Boolean,
    @Json(name = "Data")
    val values: List<DataX>,
    @Json(name = "TimeFrom")
    val timeFrom: Int,
    @Json(name = "TimeTo")
    val timeTo: Int
)
