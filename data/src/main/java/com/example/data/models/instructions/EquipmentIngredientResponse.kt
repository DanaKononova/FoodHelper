package com.example.data.models.instructions

import com.google.gson.annotations.SerializedName

data class EquipmentIngredientResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("image") val image: String? = null
)