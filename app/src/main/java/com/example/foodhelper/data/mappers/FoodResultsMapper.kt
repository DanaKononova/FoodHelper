package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.db.food.FoodEntity
import com.example.foodhelper.data.models.FoodResultsResponse
import javax.inject.Inject

class FoodResultsMapper @Inject constructor() {
    operator fun invoke(foodResultsResponse: FoodResultsResponse) = with(foodResultsResponse) {
        FoodEntity(
            id = foodResultsResponse.id ?: 0,
            title = foodResultsResponse.title ?: "",
            image = foodResultsResponse.image ?: "",
            imageType = foodResultsResponse.imageType ?: ""
        )
    }
}