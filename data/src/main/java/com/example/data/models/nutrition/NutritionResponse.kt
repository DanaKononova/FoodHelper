package com.example.data.models.nutrition

import com.squareup.moshi.Json

class NutritionResponse {
    @Json(name = "nutrients") val nutrients: List<NutrientsListResponse>? = null
}