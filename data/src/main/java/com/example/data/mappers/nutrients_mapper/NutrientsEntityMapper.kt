package com.example.data.mappers.nutrients_mapper

import com.example.data.db.nutrition.NutritionEntity
import com.example.domain.models.nutrients.NutrientsData
import javax.inject.Inject

class NutrientsEntityMapper @Inject constructor() {
    operator fun invoke(nutritionEntity: NutritionEntity) = with(nutritionEntity) {
        NutrientsData(
            title = nutritionEntity.title ?: "",
//            amount = nutritionEntity.amount ?: "",
//            percentOfDailyNeeds = nutritionEntity.percentOfDailyNeeds ?: 0.0f
        )
    }
}