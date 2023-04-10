package com.example.data.mappers.generate_template_mapper

import com.example.data.db.meal_plan.*
import com.example.data.models.generate_template.GenerateMealsResponse
import com.example.data.models.generate_template.NutrientsTemplateResponse
import com.example.domain.models.generate_template.GenerateMealsData
import com.example.domain.models.generate_template.NutrientsTemplateData
import javax.inject.Inject

class TemplateEntityMapper @Inject constructor() {

    fun mondayMealsMapper(elements: List<MondayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun tuesdayMealsMapper(elements: List<TuesdayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun wednesdayMealsMapper(elements: List<WednesdayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun thursdayMealsMapper(elements: List<ThursdayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun fridayMealsMapper(elements: List<FridayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun saturdayMealsMapper(elements: List<SaturdayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun sundayMealsMapper(elements: List<SundayEntity>): List<GenerateMealsData> {
        return elements.map {
            GenerateMealsData(
                id = it.id,
                title = it.title ,
                readyInMinutes = it.readyInMinutes,
                servings = it.servings,
                sourceUrl = it.sourceUrl,
                imageType = it.imageType
            )
        }
    }

    fun nutrientsMapper(nutrientsEntity: NutrientsEntity): NutrientsTemplateData {
        return NutrientsTemplateData(
            calories = nutrientsEntity.calories,
            carbohydrates = nutrientsEntity.carbohydrates,
            fat = nutrientsEntity.fat,
            protein = nutrientsEntity.protein
        )
    }
}