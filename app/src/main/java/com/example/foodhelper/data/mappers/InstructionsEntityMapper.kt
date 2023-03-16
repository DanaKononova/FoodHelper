package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.instructions.EquipmentIngredientsEntity
import com.example.foodhelper.data.db.instructions.InstructionsEntity
import com.example.foodhelper.data.models.EquipmentIngredientResponse
import com.example.foodhelper.domain.models.EquipmentIngredientData
import com.example.foodhelper.domain.models.InstructionsData
import javax.inject.Inject

class InstructionsEntityMapper @Inject constructor() {
    operator fun invoke(instructionsEntity: InstructionsEntity, equipmentEntity: List<EquipmentIngredientsEntity>, ingredientsEntity: List<EquipmentIngredientsEntity>) =
        with(instructionsEntity) {
            InstructionsData(
                equipment = if (equipmentEntity != null) equipmentIngredientMapper(
                    equipmentEntity
                ) else listOf(),
                ingredient = if (ingredientsEntity != null) equipmentIngredientMapper(
                    ingredientsEntity
                ) else listOf(),
                number = instructionsEntity.number ?: 0,
                step = instructionsEntity.step ?: ""
            )
        }

    private fun equipmentIngredientMapper(elements: List<EquipmentIngredientsEntity>): List<EquipmentIngredientData> {
        return elements.map {
            EquipmentIngredientData(
                id = it.id ?: 0,
                name = it.name ?: "",
                image = it.image ?: ""
            )
        }
    }
}