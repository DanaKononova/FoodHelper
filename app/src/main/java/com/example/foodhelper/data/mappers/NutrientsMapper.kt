package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.nutrition.NutritionEntity
import com.example.foodhelper.data.models.NutrientsListResponse
import com.example.foodhelper.domain.models.NutrientsData
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