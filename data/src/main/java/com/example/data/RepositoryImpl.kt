package com.example.data

import com.example.data.db.instructions.EquipmentIngredientsEntity
import com.example.data.db.meal_plan.NutrientsEntity
import com.example.data.mappers.food_mapper.FoodEntityMapper
import com.example.data.mappers.food_mapper.FoodResultsMapper
import com.example.data.mappers.generate_template_mapper.GenerateTemplateMapper
import com.example.data.mappers.generate_template_mapper.TemplateEntityMapper
import com.example.data.mappers.get_templates_mapper.TemplatesMapper
import com.example.data.mappers.instructions_mapper.InstructionsEntityMapper
import com.example.data.mappers.instructions_mapper.InstructionsMapper
import com.example.data.mappers.nutrients_mapper.NutrientsEntityMapper
import com.example.data.mappers.nutrients_mapper.NutrientsMapper
import com.example.data.mappers.user_mapper.UserMapper
import com.example.data.network.*
import com.example.data.source.*
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
    private val templateEntityMapper: TemplateEntityMapper,
    private val templatesMapper: TemplatesMapper,
    private val userSource: UserDataSource,
    private val foodDataBaseSource: FoodDataBaseSource,
    private val nutritionDataBaseSource: NutritionDataBaseSource,
    private val instructionsDataBaseSource: InstructionsDataBaseSource,
    private val mealPlanDBSource: MealPlanDataBaseSource
) : Repository {
    override suspend fun getFoodList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response = (foodService.getFood(query, userSource.getUserToken())).results
                val foodList = (response ?: listOf()).map { foodMapper(it) }
                foodList.map { foodEntityMapper(it) }
            } else emptyList()
        }
    }

    override suspend fun getBreakfastList(query: String, isConnected: Boolean): List<FoodData> {
        return withContext(Dispatchers.IO) {
            if (isConnected) {
                val response = (foodService.getFood(query, userSource.getUserToken()))

                val foodList = (response.results ?: listOf()).map { foodMapper(it) }
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
                    (foodService.getFood(query, userSource.getUserToken())).results
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
                    (foodService.getFood(query, userSource.getUserToken())).results
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
                    (foodService.getFood(query, userSource.getUserToken())).results
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
                    (nutritionService.getNutrition(id, userSource.getUserToken())).good
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
                    (analyzedInstructionService.getInstruction(id, userSource.getUserToken()))
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
                userService.getUser(
                    User(username, firstName, lastName, email),
                    userSource.getUserToken()
                )
            val user = userMapper(response)
            user
        }
    }

    override suspend fun getTemplates(username: String, hash: String): List<TemplatesData> {
        return withContext(Dispatchers.IO) {
            val response =
                (mealTemplatesService.getTemplates(username, hash, userSource.getUserToken())).templates
            val templates = (response ?: listOf()).map { templatesMapper(it) }
            templates
        }
    }

    override suspend fun weekTemplateToDB(
        timeFrame: String,
        targetCalories: String,
        diet: String,
        exclude: String,
    ) {
        withContext(Dispatchers.IO) {
            val response = generateTemplateService.generateWeekTemplate(timeFrame, targetCalories, diet, exclude, userSource.getUserToken()).week
            mealPlanDBSource.deleteAllMonday(mealPlanDBSource.getAllMonday())
            mealPlanDBSource.deleteAllTuesday(mealPlanDBSource.getAllTuesday())
            mealPlanDBSource.deleteAllWednesday(mealPlanDBSource.getAllWednesday())
            mealPlanDBSource.deleteAllThursday(mealPlanDBSource.getAllThursday())
            mealPlanDBSource.deleteAllFriday(mealPlanDBSource.getAllFriday())
            mealPlanDBSource.deleteAllSaturday(mealPlanDBSource.getAllSaturday())
            mealPlanDBSource.deleteAllSunday(mealPlanDBSource.getAllSunday())
            mealPlanDBSource.deleteAllNutrients(mealPlanDBSource.getAllNutrients())

            response?.monday?.let { monday ->
                monday.meals?.let { generateTemplateMapper.toMondayEntity(it) }?.let { mealPlanDBSource.insertAllMonday(it) }
                monday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.tuesday?.let { tuesday ->
                tuesday.meals?.let { generateTemplateMapper.toTuesdayEntity(it) }?.let { mealPlanDBSource.insertAllTuesday(it) }
                tuesday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.wednesday?.let { wednesday ->
                wednesday.meals?.let { generateTemplateMapper.toWednesdayEntity(it) }?.let { mealPlanDBSource.insertAllWednesday(it) }
                wednesday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.thursday?.let { thursday ->
                thursday.meals?.let { generateTemplateMapper.toThursdayEntity(it) }?.let { mealPlanDBSource.insertAllThursday(it) }
                thursday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.friday?.let { friday ->
                friday.meals?.let { generateTemplateMapper.toFridayEntity(it) }?.let { mealPlanDBSource.insertAllFriday(it) }
                friday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.saturday?.let { saturday ->
                saturday.meals?.let { generateTemplateMapper.toSaturdayEntity(it) }?.let { mealPlanDBSource.insertAllSaturday(it) }
                saturday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
            response?.sunday?.let { sunday ->
                sunday.meals?.let { generateTemplateMapper.toSundayEntity(it) }?.let { mealPlanDBSource.insertAllSunday(it) }
                sunday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }?.let { mealPlanDBSource.insertAllNutrients(it) }
            }
        }
    }

    override suspend fun generateDayTemplate(
        day: String
    ): GenerateTemplateData {
        val nutrientsList = mealPlanDBSource.getAllNutrients()
        return when (day) {
            "monday" -> GenerateTemplateData(templateEntityMapper.mondayMealsMapper(mealPlanDBSource.getAllMonday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[0]))
            "tuesday" -> GenerateTemplateData(templateEntityMapper.tuesdayMealsMapper(mealPlanDBSource.getAllTuesday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[1]))
            "wednesday" -> GenerateTemplateData(templateEntityMapper.wednesdayMealsMapper(mealPlanDBSource.getAllWednesday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[2]))
            "thursday" -> GenerateTemplateData(templateEntityMapper.thursdayMealsMapper(mealPlanDBSource.getAllThursday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[3]))
            "friday" -> GenerateTemplateData(templateEntityMapper.fridayMealsMapper(mealPlanDBSource.getAllFriday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[4]))
            "saturday" -> GenerateTemplateData(templateEntityMapper.saturdayMealsMapper(mealPlanDBSource.getAllSaturday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[5]))
            "sunday" -> GenerateTemplateData(templateEntityMapper.sundayMealsMapper(mealPlanDBSource.getAllSunday()),
                    templateEntityMapper.nutrientsMapper(nutrientsList[6]))
            else -> GenerateTemplateData(templateEntityMapper.mondayMealsMapper(mealPlanDBSource.getAllMonday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[0]))
        }
    }

    override fun setToken(token: String) {
        userSource.setUserToken(token)
    }
}