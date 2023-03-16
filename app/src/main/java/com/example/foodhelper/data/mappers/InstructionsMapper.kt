package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.instructions.EquipmentIngredientsEntity
import com.example.foodhelper.data.db.instructions.InstructionsEntity
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
            InstructionsEntity(
                equipment = if (instructionStepsResponse.equipment != null) instructionStepsResponse.equipment.size
                else 0,
                ingredients = if (instructionStepsResponse.ingredients != null) instructionStepsResponse.ingredients.size
                else 0,
                number = instructionStepsResponse.number ?: 0,
                step = instructionStepsResponse.step ?: ""
            )
        }

    fun equipmentIngredientMapper(elements: List<EquipmentIngredientResponse>): List<EquipmentIngredientsEntity> {
        return elements.map {
            EquipmentIngredientsEntity(
                id = it.id ?: 0,
                name = it.name ?: "",
                image = it.image ?: ""
            )
        }
    }
}