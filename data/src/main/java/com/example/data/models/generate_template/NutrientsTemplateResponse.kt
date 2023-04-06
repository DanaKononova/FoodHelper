package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class NutrientsTemplateResponse(
    @SerializedName("calories") val calories: Double? = null,
    @SerializedName("carbohydrates") val carbohydrates: Double? = null,
    @SerializedName("fat") val fat: Double? = null,
    @SerializedName("protein") val protein: Double? = null,
)