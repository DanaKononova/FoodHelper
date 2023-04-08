package com.example.data.network

import com.example.data.models.food.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("recipes/complexSearch")
    fun getFood(
        @Query("query") query: String,
        @Query("apiKey") token: String
    ): Call<FoodResponse>
}