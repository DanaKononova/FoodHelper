package com.example.foodhelper.data

import com.example.foodhelper.data.db.instructions.EquipmentIngredientsEntity
import com.example.foodhelper.data.mappers.*
import com.example.foodhelper.data.network.AnalyzedInstructionService
import com.example.foodhelper.data.network.FoodService
import com.example.foodhelper.data.network.NutritionService
import com.example.foodhelper.data.source.FoodDataBaseSource
import com.example.foodhelper.data.source.InstructionsDataBaseSource
import com.example.foodhelper.data.source.NutritionDataBaseSource
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
    private val foodEntityMapper: FoodEntityMapper,
    private val nutrientsMapper: NutrientsMapper,
    private val nutrientsEntityMapper: NutrientsEntityMapper,
    private val instructionsMapper: InstructionsMapper,
    private val instructionsEntityMapper: InstructionsEntityMapper,
    private val foodDataBaseSource: FoodDataBaseSource,
    private val nutritionDataBaseSource: NutritionDataBaseSource,
    private val instructionsDataBaseSource: InstructionsDataBaseSource
) : Repository {
    override suspend fun getFoodList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (foodService.getFood(query, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).results
                val foodList = (response ?: listOf()).map { foodMapper(it) }
                foodDataBaseSource.delete(foodDataBaseSource.getAll())
                foodDataBaseSource.insertAll(foodList)
                foodList.map { foodEntityMapper(it) }
            } else {
                foodDataBaseSource.getAll().map { foodEntityMapper(it) }
            }
        }
    }

    override suspend fun getNutrientsList(id: String, isConnected: Boolean): List<NutrientsData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (nutritionService.getNutrition(id, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).nutrients
                val nutrientsList = (response ?: listOf()).map { nutrientsMapper(it) }
                nutritionDataBaseSource.delete(nutritionDataBaseSource.getAll())
                nutritionDataBaseSource.insertAll(nutrientsList)
                nutrientsList.map { nutrientsEntityMapper(it) }
            } else {
                nutritionDataBaseSource.getAll().map { nutrientsEntityMapper(it) }
            }
        }
    }

    override suspend fun getInstructionsList(id: String, isConnected: Boolean)
            : List<InstructionsData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (analyzedInstructionService.getInstruction(id, userSource.getUserToken())
                        .execute().body() ?: throw Exception())
                instructionsDataBaseSource.deleteInstructions(instructionsDataBaseSource.getAllInstructions())
                instructionsDataBaseSource.deleteEquipmentIngredients(instructionsDataBaseSource.getAllEquipmentIngredients())
                var equipmentEntity: List<EquipmentIngredientsEntity> = listOf()
                var ingredientsEntity: List<EquipmentIngredientsEntity> = listOf()
                val instructionsList =
                    response[0].steps?.map {
                        val instructionsEntity = instructionsMapper(it)
                        if (it.equipment != null) {
                            equipmentEntity =
                                instructionsMapper.equipmentIngredientMapper(it.equipment)
                            instructionsDataBaseSource.insertAllEquipmentIngredients(equipmentEntity)
                        }
                        if (it.ingredients != null) {
                            ingredientsEntity =
                                instructionsMapper.equipmentIngredientMapper(it.ingredients)
                            instructionsDataBaseSource.insertAllEquipmentIngredients(
                                ingredientsEntity
                            )
                        }
                        instructionsDataBaseSource.insertAllInstructions(instructionsEntity)
                        instructionsEntityMapper(
                            instructionsEntity,
                            equipmentEntity,
                            ingredientsEntity
                        )
                    } ?: listOf()
                instructionsList
            } else {
                val equipmentIngredientsList =
                    instructionsDataBaseSource.getAllEquipmentIngredients()
                var equipmentEntity: List<EquipmentIngredientsEntity> = listOf()
                var ingredientsEntity: List<EquipmentIngredientsEntity> = listOf()
                var j = 0;
                instructionsDataBaseSource.getAllInstructions()
                    .map {
                            equipmentEntity = equipmentIngredientsList.subList(j, j + it.equipment)
                            j += it.equipment
                            ingredientsEntity = equipmentIngredientsList.subList(j, j + it.ingredients)
                            j += it.ingredients
                            instructionsEntityMapper(it, equipmentEntity, ingredientsEntity)
                    }
            }
        }
    }

    override fun setToken(token: String) {
        userSource.setUserToken(token)
    }
}