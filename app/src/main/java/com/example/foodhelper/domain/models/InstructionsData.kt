package com.example.foodhelper.domain.models

data class InstructionsData(
    val equipment: List<EquipmentIngredientData>,
    val ingredient: List<EquipmentIngredientData>,
    val number: Int,
    val step: String
)