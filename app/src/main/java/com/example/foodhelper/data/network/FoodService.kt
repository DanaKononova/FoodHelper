package com.example.foodhelper.data.network

import com.example.foodhelper.data.models.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("complexSearch")
    fun getFood(
        @Query("query") query: String,
        @Query("apiKey") token: String
    ): Call<FoodResponse>
}