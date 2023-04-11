package com.example.data.models.nutrition

import com.squareup.moshi.Json

data class NutrientsListResponse(
    @Json(name = "name") val name: String? = null,
    @Json(name = "amount") val amount: Float? = null,
    @Json(name = "percentOfDailyNeeds") val percentOfDailyNeeds: Float? = null
)