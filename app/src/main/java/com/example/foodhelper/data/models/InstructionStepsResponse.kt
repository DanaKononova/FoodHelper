package com.example.foodhelper.data.models

import com.google.gson.annotations.SerializedName

data class InstructionStepsResponse (
    @SerializedName("equipment") val equipment: List<EquipmentIngredientResponse>? = null,
    @SerializedName("ingredients") val ingredients: List<EquipmentIngredientResponse>? = null,
    @SerializedName("number") val number: Int? = null,
    @SerializedName("step") val step: String? = null
)