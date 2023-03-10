package com.example.foodhelper.data.models

import com.google.gson.annotations.SerializedName

data class EquipmentIngredientResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("image") val image: String? = null
)