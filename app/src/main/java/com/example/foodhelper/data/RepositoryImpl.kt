package com.example.foodhelper.data

import com.example.foodhelper.data.mappers.FoodResultsMapper
import com.example.foodhelper.data.mappers.InstructionsMapper
import com.example.foodhelper.data.mappers.NutrientsMapper
import com.example.foodhelper.data.network.AnalyzedInstructionService
import com.example.foodhelper.data.network.FoodService
import com.example.foodhelper.data.network.NutritionService
import com.example.foodhelper.data.source.UserDataSource
import com.example.foodhelper.domain.Repository
import com.example.foodhelper.domain.models.FoodData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val foodService: FoodService,
    private val nutritionService: NutritionService,
    private val analyzedInstructionService: AnalyzedInstructionService,
    private val userSource: UserDataSource,
    private val foodMapper: FoodResultsMapper,
    private val nutrientsMapper: NutrientsMapper,
    private val instructionsMapper: InstructionsMapper
) : Repository {
    override suspend fun getFoodList(query: String): List<FoodData> {
        return withContext(Dispatchers.IO) {
            val foodList =
                (foodService.getFood(query, userSource.getUserToken()).execute().body()
                    ?: throw Exception()).results
            if (foodList != null) {
                foodList.map { foodMapper(it) }
            } else listOf()
        }
    }

    override suspend fun getNutrientsList(id: String): List<NutrientsData> {
        return withContext(Dispatchers.IO) {
            val nutrientsList =
                (nutritionService.getNutrition(id, userSource.getUserToken()).execute().body()
                    ?: throw Exception()).nutrients
            if (nutrientsList != null) {
                nutrientsList.map { nutrientsMapper(it) }
            } else listOf()
        }
    }

    override suspend fun getInstructionsList(id: String): List<List<InstructionsData>> {
        return withContext(Dispatchers.IO) {
            val instructionsList =
                (analyzedInstructionService.getInstruction(id, userSource.getUserToken()).execute().body()
                    ?: throw Exception())
            if (instructionsList != null) {
                instructionsList.map { it.steps?.map { instructionsMapper(it) } ?: listOf() }
            } else listOf()
        }
    }

    override fun setToken(token: String) {
        userSource.setUserToken(token)
    }
}