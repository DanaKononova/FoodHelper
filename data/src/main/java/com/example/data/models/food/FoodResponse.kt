package com.example.data.models.food

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("results") val results: List<FoodResultsResponse>? = null
)