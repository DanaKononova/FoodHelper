package com.example.data.mappers.generate_template_mapper

import com.example.data.db.instructions.EquipmentIngredientsEntity
import com.example.data.db.instructions.InstructionsEntity
import com.example.data.db.meal_plan.*
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

    fun toMondayEntity(elements: List<GenerateMealsResponse>): List<MondayEntity> {
        return elements.map {
            MondayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toTuesdayEntity(elements: List<GenerateMealsResponse>): List<TuesdayEntity> {
        return elements.map {
            TuesdayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toWednesdayEntity(elements: List<GenerateMealsResponse>): List<WednesdayEntity> {
        return elements.map {
            WednesdayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toThursdayEntity(elements: List<GenerateMealsResponse>): List<ThursdayEntity> {
        return elements.map {
            ThursdayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toFridayEntity(elements: List<GenerateMealsResponse>): List<FridayEntity> {
        return elements.map {
            FridayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toSaturdayEntity(elements: List<GenerateMealsResponse>): List<SaturdayEntity> {
        return elements.map {
            SaturdayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toSundayEntity(elements: List<GenerateMealsResponse>): List<SundayEntity> {
        return elements.map {
            SundayEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                readyInMinutes = it.readyInMinutes ?: 0,
                servings = it.servings ?: 0,
                sourceUrl = it.sourceUrl ?: "",
                imageType = it.imageType ?: ""
            )
        }
    }

    fun toNutrientsEntity(elements: NutrientsTemplateResponse): NutrientsEntity {
        return NutrientsEntity(
            calories = elements.calories ?: 0.0,
            carbohydrates = elements.carbohydrates ?: 0.0,
            fat = elements.fat ?: 0.0,
            protein = elements.protein ?: 0.0
        )
    }
}