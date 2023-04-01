package com.example.data.mappers

import com.example.data.db.nutrition.NutritionEntity
import com.example.domain.models.NutrientsData
import javax.inject.Inject

class NutrientsEntityMapper @Inject constructor() {
    operator fun invoke(nutritionEntity: NutritionEntity) = with(nutritionEntity) {
        NutrientsData(
            name = nutritionEntity.name ?: "",
            amount = nutritionEntity.amount ?: "",
            percentOfDailyNeeds = nutritionEntity.percentOfDailyNeeds ?: 0.0f
        )
    }
}