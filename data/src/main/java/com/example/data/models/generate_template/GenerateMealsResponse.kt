package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class GenerateMealsResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("readyInMinutes") val readyInMinutes: Int? = null,
    @SerializedName("servings") val servings: Int? = null,
    @SerializedName("sourceUrl") val sourceUrl: String? = null,
    @SerializedName("imageType") val imageType: String? = null,
)