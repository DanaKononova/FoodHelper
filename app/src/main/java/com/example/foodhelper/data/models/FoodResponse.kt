package com.example.foodhelper.data.models

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("results") val results: List<FoodResultsResponse>? = null
)