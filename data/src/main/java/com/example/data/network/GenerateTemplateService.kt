package com.example.data.network

import com.example.data.models.generate_template.GenerateTemplateResponse
import com.example.data.models.generate_template.GenerateWeekResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenerateTemplateService {
    @GET("mealplanner/generate")
    fun generateWeekTemplate(
        @Query("timeFrame") timeFrame: String,
        @Query("targetCalories") targetCalories: String,
        @Query("diet") diet: String,
        @Query("exclude") exclude: String,
        @Query("apiKey") token: String
    ): Call<GenerateWeekResponse>

    @GET("mealplanner/generate")
    fun generateDayTemplate(
        @Query("timeFrame") timeFrame: String,
        @Query("targetCalories") targetCalories: String,
        @Query("diet") diet: String,
        @Query("exclude") exclude: String,
        @Query("apiKey") token: String
    ): Call<GenerateTemplateResponse>
}