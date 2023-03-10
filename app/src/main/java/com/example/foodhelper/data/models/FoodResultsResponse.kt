package com.example.foodhelper.data.models

import com.google.gson.annotations.SerializedName

data class FoodResultsResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("imageType") val imageType: String? = null
)