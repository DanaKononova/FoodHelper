package com.example.domain

import com.example.domain.models.food.FoodData
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.get_templates.TemplatesData
import com.example.domain.models.instructions.InstructionsData
import com.example.domain.models.nutrients.NutrientsData
import com.example.domain.models.user.UserData

interface Repository {
    suspend fun getFoodList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getBreakfastList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getBrunchList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getLunchList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getDinnerList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getNutrientsList(id: String, isConnected: Boolean): List<NutrientsData>

    suspend fun getInstructionsList(id: String, isConnected: Boolean): List<InstructionsData>

    suspend fun getUserInfo(username: String, firstName: String, lastName: String, email: String): UserData

    suspend fun getTemplates(username: String, hash: String): List<TemplatesData>

    suspend fun weekTemplateToDB(timeFrame: String, targetCalories: String, diet: String, exclude: String)

    suspend fun generateDayTemplate(day: String): GenerateTemplateData

    fun setToken(token: String)
}