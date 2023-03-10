package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.models.EquipmentIngredientResponse
import com.example.foodhelper.data.models.InstructionStepsResponse
import com.example.foodhelper.data.models.InstructionsResponse
import com.example.foodhelper.data.models.NutrientsListResponse
import com.example.foodhelper.domain.models.EquipmentIngredientData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData
import javax.inject.Inject

class InstructionsMapper @Inject constructor() {
    operator fun invoke(instructionStepsResponse: InstructionStepsResponse) =
        with(instructionStepsResponse) {
            InstructionsData(
                equipment = if (instructionStepsResponse.equipment != null) equipmentIngredientMapper(
                    instructionStepsResponse.equipment
                ) else listOf(),
                ingredient = if (instructionStepsResponse.ingredients != null) equipmentIngredientMapper(
                    instructionStepsResponse.ingredients
                ) else listOf(),
                number = instructionStepsResponse.number ?: 0,
                step = instructionStepsResponse.step ?: ""
            )
        }


    private fun equipmentIngredientMapper(elements: List<EquipmentIngredientResponse>): List<EquipmentIngredientData> {
        return elements.map {
            EquipmentIngredientData(
                id = it.id ?: 0,
                name = it.name ?: "",
                image = it.image ?: ""
            )
        }
    }
}