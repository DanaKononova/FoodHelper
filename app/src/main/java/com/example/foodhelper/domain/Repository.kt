package com.example.foodhelper.domain

import com.example.foodhelper.data.db.food.FoodEntity
import com.example.foodhelper.domain.models.FoodData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData

interface Repository {
    suspend fun getFoodList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getNutrientsList(id: String, isConnected: Boolean): List<NutrientsData>

    suspend fun getInstructionsList(id: String, isConnected: Boolean): List<InstructionsData>

    fun setToken(token: String)
}