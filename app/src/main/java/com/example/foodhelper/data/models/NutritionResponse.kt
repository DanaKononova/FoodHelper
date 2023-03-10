package com.example.foodhelper.data.models

import com.google.gson.annotations.SerializedName

class NutritionResponse {
    @SerializedName("bad") val nutrients: List<NutrientsListResponse>? = null
}