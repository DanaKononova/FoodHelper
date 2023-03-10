package com.example.foodhelper.domain

import com.example.foodhelper.domain.models.FoodData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData

interface Repository {
    suspend fun getFoodList(query: String): List<FoodData>

    suspend fun getNutrientsList(id: String): List<NutrientsData>

    suspend fun getInstructionsList(id: String): List<List<InstructionsData>>

    fun setToken(token: String)
}