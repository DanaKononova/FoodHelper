package com.example.data.models.nutrition

import com.google.gson.annotations.SerializedName

data class NutrientsListResponse(
    @SerializedName("title") val name: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("percentOfDailyNeeds") val percentOfDailyNeeds: Float? = null
)