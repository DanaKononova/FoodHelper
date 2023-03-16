package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.food.FoodEntity
import com.example.foodhelper.domain.models.FoodData
import javax.inject.Inject

class FoodEntityMapper @Inject constructor() {
    operator fun invoke(foodEntity: FoodEntity) = with(foodEntity) {
        FoodData(
            id = foodEntity.id ?: 0,
            title = foodEntity.title ?: "",
            image = foodEntity.image ?: "",
            imageType = foodEntity.imageType ?: ""
        )
    }
}