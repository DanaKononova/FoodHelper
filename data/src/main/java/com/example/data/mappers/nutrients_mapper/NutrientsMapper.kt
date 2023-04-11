package com.example.data.mappers.nutrients_mapper

import com.example.data.db.nutrition.NutritionEntity
import com.example.data.models.nutrition.NutrientsListResponse
import javax.inject.Inject

class NutrientsMapper @Inject constructor() {
    operator fun invoke(nutrientsListResponse: NutrientsListResponse) =
        with(nutrientsListResponse) {
            NutritionEntity(
                name = nutrientsListResponse.name ?: "",
                amount = nutrientsListResponse.amount ?: 0.0f,
                percentOfDailyNeeds = nutrientsListResponse.percentOfDailyNeeds ?: 0.0f
            )
        }
}