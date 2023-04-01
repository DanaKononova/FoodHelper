package com.example.data.mappers

import com.example.data.db.nutrition.NutritionEntity
import com.example.data.models.NutrientsListResponse
import javax.inject.Inject

class NutrientsMapper @Inject constructor() {
    operator fun invoke(nutrientsListResponse: NutrientsListResponse) = with(nutrientsListResponse) {
        NutritionEntity(
            name = nutrientsListResponse.name ?: "",
            amount = nutrientsListResponse.amount ?: "",
            percentOfDailyNeeds = nutrientsListResponse.percentOfDailyNeeds ?: 0.0f
        )
    }
}