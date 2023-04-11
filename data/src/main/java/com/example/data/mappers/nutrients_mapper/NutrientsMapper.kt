package com.example.data.mappers.nutrients_mapper

import com.example.data.db.nutrition.NutritionEntity
import com.example.data.models.nutrition.NutrientsListResponse
import javax.inject.Inject

class NutrientsMapper @Inject constructor() {
    operator fun invoke(nutrientsListResponse: NutrientsListResponse) =
        with(nutrientsListResponse) {
            NutritionEntity(
                title = nutrientsListResponse.title ?: "",
                amount = nutrientsListResponse.amount ?: "",
                percentOfDailyNeeds = nutrientsListResponse.percentOfDailyNeeds ?: 0.0f
            )
        }
}