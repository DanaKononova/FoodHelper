package com.example.data.mappers.generate_template_mapper

import com.example.data.db.instructions.EquipmentIngredientsEntity
import com.example.data.db.instructions.InstructionsEntity
import com.example.data.models.generate_template.GenerateMealsResponse
import com.example.data.models.generate_template.GenerateTemplateResponse
import com.example.data.models.generate_template.NutrientsTemplateResponse
import com.example.data.models.instructions.EquipmentIngredientResponse
import com.example.data.models.instructions.InstructionStepsResponse
import com.example.domain.models.generate_template.GenerateMealsData
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.generate_template.NutrientsTemplateData
import javax.inject.Inject

class GenerateTemplateMapper @Inject constructor() {
    operator fun invoke(generateTemplateResponse: GenerateTemplateResponse) =
        with(generateTemplateResponse) {
            GenerateTemplateData(
                meals = if (generateTemplateResponse.meals != null) mealsMapper(
                    generateTemplateResponse.meals
                )
                else emptyList(),
                nutrients = if (generateTemplateResponse.nutrients != null) nutrientsMapper(
                    generateTemplateResponse.nutrients
                )
                else NutrientsTemplateData(0, 0, 0, 0),
            )
        }

    fun mealsMapper(elements: List<GenerateMealsResponse>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: ""
            )
        }
    }

    fun nutrientsMapper(elements: NutrientsTemplateResponse): NutrientsTemplateData {
        return NutrientsTemplateData(
            calories = elements.calories ?: 0,
            carbohydrates = elements.carbohydrates ?: 0,
            fat = elements.fat ?: 0,
            protein = elements.protein ?: 0
        )
    }
}