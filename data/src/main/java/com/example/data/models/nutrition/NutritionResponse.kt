package com.example.data.models.nutrition

import com.google.gson.annotations.SerializedName

class NutritionResponse {
    @SerializedName("bad") val nutrients: List<NutrientsListResponse>? = null
}