package com.example.foodhelper.data.mappers

import com.example.foodhelper.data.models.FoodResultsResponse
import com.example.foodhelper.domain.models.FoodData
import javax.inject.Inject

class FoodResultsMapper @Inject constructor() {
    operator fun invoke(foodResultsResponse: FoodResultsResponse) = with(foodResultsResponse) {
        FoodData(
            id = foodResultsResponse.id ?: 0,
            title = foodResultsResponse.title ?: "",
            image = foodResultsResponse.image ?: "",
            imageType = foodResultsResponse.imageType ?: ""
        )
    }
}