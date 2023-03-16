package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.nutrition.NutritionEntity
import com.example.foodhelper.data.models.NutrientsListResponse
import com.example.foodhelper.domain.models.NutrientsData
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