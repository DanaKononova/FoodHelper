package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class NutrientsTemplateResponse(
    @SerializedName("calories") val calories: Int? = null,
    @SerializedName("carbohydrates") val carbohydrates: Int? = null,
    @SerializedName("fat") val fat: Int? = null,
    @SerializedName("protein") val protein: Int? = null,
)