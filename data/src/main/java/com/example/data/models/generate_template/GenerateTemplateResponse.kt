package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class GenerateTemplateResponse(
    @SerializedName("meals") val meals: List<GenerateMealsResponse>? = null,
    @SerializedName("nutrients") val nutrients: NutrientsTemplateResponse? = null
)