package com.example.data.network

import com.example.data.models.get_templates.TemplatesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealTemplatesService {
    @GET("mealplanner/{username}/templates")
    fun getTemplates(
        @Path("username") username: String,
        @Query("hash") hash: String,
        @Query("apiKey") token: String
    ): Call<TemplatesResponse>
}