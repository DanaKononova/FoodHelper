package com.example.data.models.nutrition

import com.squareup.moshi.Json

class NutritionResponse {
    @Json(name = "good") val good: List<NutrientsListResponse>? = null
}