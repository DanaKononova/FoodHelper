package com.example.data.network

import com.example.data.models.nutrition.NutritionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NutritionService {
    @GET("recipes/{id}/nutritionWidget.json")
    fun getNutrition(
        @Path("id") id: String,
        @Query("apiKey") token: String
    ): Call<NutritionResponse>
}