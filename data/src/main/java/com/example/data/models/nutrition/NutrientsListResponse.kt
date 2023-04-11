package com.example.data.models.nutrition

import com.squareup.moshi.Json

data class NutrientsListResponse(
    @Json(name = "title") val title: String? = null,
    @Json(name = "amount") val amount: String? = null,
    @Json(name = "percentOfDailyNeeds") val percentOfDailyNeeds: Float? = null
)