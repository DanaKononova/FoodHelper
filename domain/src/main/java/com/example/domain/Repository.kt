package com.example.domain

import com.example.domain.models.FoodData
import com.example.domain.models.InstructionsData
import com.example.domain.models.NutrientsData

interface Repository {
    suspend fun getFoodList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getBreakfastList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getBrunchList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getLunchList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getDinnerList(query: String, isConnected: Boolean): List<FoodData>

    suspend fun getNutrientsList(id: String, isConnected: Boolean): List<NutrientsData>

    suspend fun getInstructionsList(id: String, isConnected: Boolean): List<InstructionsData>

    fun setToken(token: String)
}