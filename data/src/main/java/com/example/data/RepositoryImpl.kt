package com.example.data

import com.example.data.db.instructions.EquipmentIngredientsEntity
import com.example.data.mappers.food_mapper.FoodEntityMapper
import com.example.data.mappers.food_mapper.FoodResultsMapper
import com.example.data.mappers.generate_template_mapper.GenerateTemplateMapper
import com.example.data.mappers.get_templates_mapper.TemplatesMapper
import com.example.data.mappers.instructions_mapper.InstructionsEntityMapper
import com.example.data.mappers.instructions_mapper.InstructionsMapper
import com.example.data.mappers.nutrients_mapper.NutrientsEntityMapper
import com.example.data.mappers.nutrients_mapper.NutrientsMapper
import com.example.data.mappers.user_mapper.UserMapper
import com.example.data.network.*
import com.example.data.source.FoodDataBaseSource
import com.example.data.source.InstructionsDataBaseSource
import com.example.data.source.NutritionDataBaseSource
import com.example.data.source.UserDataSource
import com.example.domain.Repository
import com.example.domain.models.food.FoodData
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.get_templates.TemplatesData
import com.example.domain.models.instructions.InstructionsData
import com.example.domain.models.nutrients.NutrientsData
import com.example.domain.models.user.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val foodService: FoodService,
    private val nutritionService: NutritionService,
    private val analyzedInstructionService: AnalyzedInstructionService,
    private val userService: ContactUserService,
    private val mealTemplatesService: MealTemplatesService,
    private val generateTemplateService: GenerateTemplateService,
    private val foodMapper: FoodResultsMapper,
    private val foodEntityMapper: FoodEntityMapper,
    private val nutrientsMapper: NutrientsMapper,
    private val nutrientsEntityMapper: NutrientsEntityMapper,
    private val instructionsMapper: InstructionsMapper,
    private val instructionsEntityMapper: InstructionsEntityMapper,
    private val userMapper: UserMapper,
    private val generateTemplateMapper: GenerateTemplateMapper,
    private val templatesMapper: TemplatesMapper,
    private val userSource: UserDataSource,
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
                foodList.map { foodEntityMapper(it) }
            } else emptyList()
        }
    }

    override suspend fun getBreakfastList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (foodService.getFood(query, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).results
                val foodList = (response ?: listOf()).map { foodMapper(it) }
                foodDataBaseSource.deleteAllBreakfast(foodDataBaseSource.getAllBreakfast())
                foodDataBaseSource.insertAllBreakfast(foodList)
                foodList.map { foodEntityMapper(it) }
            } else {
                foodDataBaseSource.getAllBreakfast().map { foodEntityMapper(it) }
            }
        }
    }

    override suspend fun getBrunchList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (foodService.getFood(query, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).results
                val foodList = (response ?: listOf()).map { foodMapper.toBrunchEntity(it) }
                foodDataBaseSource.deleteAllBrunch(foodDataBaseSource.getAllBrunch())
                foodDataBaseSource.insertAllBrunch(foodList)
                foodList.map { foodEntityMapper(it) }
            } else {
                foodDataBaseSource.getAllBrunch().map { foodEntityMapper(it) }
            }
        }
    }

    override suspend fun getLunchList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (foodService.getFood(query, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).results
                val foodList = (response ?: listOf()).map { foodMapper.toLunchEntity(it) }
                foodDataBaseSource.deleteAllLunch(foodDataBaseSource.getAllLunch())
                foodDataBaseSource.insertAllLunch(foodList)
                foodList.map { foodEntityMapper(it) }
            } else {
                foodDataBaseSource.getAllLunch().map { foodEntityMapper(it) }
            }
        }
    }

    override suspend fun getDinnerList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response =
                    (foodService.getFood(query, userSource.getUserToken()).execute().body()
                        ?: throw Exception()).results
                val foodList = (response ?: listOf()).map { foodMapper.toDinnerEntity(it) }
                foodDataBaseSource.deleteAllDinner(foodDataBaseSource.getAllDinner())
                foodDataBaseSource.insertAllDinner(foodList)
                foodList.map { foodEntityMapper(it) }
            } else {
                foodDataBaseSource.getAllDinner().map { foodEntityMapper(it) }
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
                var equipmentEntity: List<EquipmentIngredientsEntity>
                var ingredientsEntity: List<EquipmentIngredientsEntity>
                var j = 0
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

    override suspend fun getUserInfo(
        username: String,
        firstName: String,
        lastName: String,
        email: String
    ): UserData {
        return withContext(Dispatchers.IO) {
            val response =
                (userService.getUser(
                    User(username, firstName, lastName, email),
                    userSource.getUserToken()
                ).execute().body() ?: throw Exception())
            val user = userMapper(response)
            user
        }
    }

    override suspend fun getTemplates(username: String, hash: String): List<TemplatesData> {
        return withContext(Dispatchers.IO) {
            val response =
                (mealTemplatesService.getTemplates(username, hash, userSource.getUserToken())
                    .execute().body()
                    ?: throw Exception()).templates
            val templates = (response ?: listOf()).map { templatesMapper(it) }
            templates
        }
    }

    override suspend fun generateTemplate(
        timeFrame: String,
        targetCalories: String,
        diet: String,
        exclude: String
    ): GenerateTemplateData {
        return withContext(Dispatchers.IO) {
            val response =
                (generateTemplateService.generateTemplate(
                    timeFrame,
                    targetCalories.toInt(),
                    diet,
                    exclude,
                    userSource.getUserToken()
                ).execute().body() ?: throw Exception())
            val generatedTemplate = generateTemplateMapper(response)
            generatedTemplate
        }
    }

    override fun setToken(token: String) {
        userSource.setUserToken(token)
    }
}