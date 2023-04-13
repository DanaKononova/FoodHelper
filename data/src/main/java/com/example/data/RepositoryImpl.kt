package com.example.data

import com.example.data.db.instructions.EquipmentIngredientsEntity
import com.example.data.mappers.food_mapper.FoodEntityMapper
import com.example.data.mappers.food_mapper.FoodResultsMapper
import com.example.data.mappers.generate_template_mapper.CurrentToMealPlansMapper
import com.example.data.mappers.generate_template_mapper.GenerateTemplateMapper
import com.example.data.mappers.generate_template_mapper.TemplateEntityMapper
import com.example.data.mappers.get_templates_mapper.TemplatesMapper
import com.example.data.mappers.instructions_mapper.InstructionsEntityMapper
import com.example.data.mappers.instructions_mapper.InstructionsMapper
import com.example.data.mappers.nutrients_mapper.NutrientsEntityMapper
import com.example.data.mappers.nutrients_mapper.NutrientsMapper
import com.example.data.network.*
import com.example.data.source.*
import com.example.domain.Repository
import com.example.domain.models.food.FoodData
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.instructions.InstructionsData
import com.example.domain.models.nutrients.NutrientsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val foodService: FoodService,
    private val nutritionService: NutritionService,
    private val analyzedInstructionService: AnalyzedInstructionService,
    private val mealTemplatesService: MealTemplatesService,
    private val generateTemplateService: GenerateTemplateService,
    private val foodMapper: FoodResultsMapper,
    private val foodEntityMapper: FoodEntityMapper,
    private val nutrientsMapper: NutrientsMapper,
    private val nutrientsEntityMapper: NutrientsEntityMapper,
    private val instructionsMapper: InstructionsMapper,
    private val instructionsEntityMapper: InstructionsEntityMapper,
    private val generateTemplateMapper: GenerateTemplateMapper,
    private val templateEntityMapper: TemplateEntityMapper,
    private val templatesMapper: TemplatesMapper,
    private val currentToMealPlansMapper: CurrentToMealPlansMapper,
    private val userSource: UserDataSource,
    private val foodDataBaseSource: FoodDataBaseSource,
    private val nutritionDataBaseSource: NutritionDataBaseSource,
    private val instructionsDataBaseSource: InstructionsDataBaseSource,
    private val currentPlanDBSource: CurrentPlanDataBaseSource,
    private val mealPlansDBSource: MealPlansDataBaseSource,
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
                    nutritionService.getNutrition(id, userSource.getUserToken())

                val nutrientsList = (response.good ?: listOf()).map { nutrientsMapper(it) }
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

    override suspend fun weekTemplateToDB(
        timeFrame: String,
        targetCalories: String,
        diet: String,
        exclude: String,
    ) {
        withContext(Dispatchers.IO) {
            val response = generateTemplateService.generateWeekTemplate(timeFrame, targetCalories, diet, exclude, userSource.getUserToken()).week
            currentPlanDBSource.deleteAllMonday(currentPlanDBSource.getAllMonday())
            currentPlanDBSource.deleteAllTuesday(currentPlanDBSource.getAllTuesday())
            currentPlanDBSource.deleteAllWednesday(currentPlanDBSource.getAllWednesday())
            currentPlanDBSource.deleteAllThursday(currentPlanDBSource.getAllThursday())
            currentPlanDBSource.deleteAllFriday(currentPlanDBSource.getAllFriday())
            currentPlanDBSource.deleteAllSaturday(currentPlanDBSource.getAllSaturday())
            currentPlanDBSource.deleteAllSunday(currentPlanDBSource.getAllSunday())
            currentPlanDBSource.deleteAllNutrients(currentPlanDBSource.getAllNutrients())

            response?.monday?.let { monday ->
                monday.meals?.let { generateTemplateMapper.toMondayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllMonday(it) }
                monday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.tuesday?.let { tuesday ->
                tuesday.meals?.let { generateTemplateMapper.toTuesdayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllTuesday(it) }
                tuesday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.wednesday?.let { wednesday ->
                wednesday.meals?.let { generateTemplateMapper.toWednesdayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllWednesday(it) }
                wednesday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.thursday?.let { thursday ->
                thursday.meals?.let { generateTemplateMapper.toThursdayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllThursday(it) }
                thursday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.friday?.let { friday ->
                friday.meals?.let { generateTemplateMapper.toFridayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllFriday(it) }
                friday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.saturday?.let { saturday ->
                saturday.meals?.let { generateTemplateMapper.toSaturdayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllSaturday(it) }
                saturday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }
            response?.sunday?.let { sunday ->
                sunday.meals?.let { generateTemplateMapper.toSundayEntity(it) }
                    ?.let { currentPlanDBSource.insertAllSunday(it) }
                sunday.nutrients?.let { generateTemplateMapper.toNutrientsEntity(it) }
                    ?.let { currentPlanDBSource.insertAllNutrients(it) }
            }

//            mealPlansDBSource.deleteAllMonday(mealPlansDBSource.getAllMonday())
//            mealPlansDBSource.deleteAllTuesday(mealPlansDBSource.getAllTuesday())
//            mealPlansDBSource.deleteAllWednesday(mealPlansDBSource.getAllWednesday())
//            mealPlansDBSource.deleteAllThursday(mealPlansDBSource.getAllThursday())
//            mealPlansDBSource.deleteAllFriday(mealPlansDBSource.getAllFriday())
//            mealPlansDBSource.deleteAllSaturday(mealPlansDBSource.getAllSaturday())
//            mealPlansDBSource.deleteAllSunday(mealPlansDBSource.getAllSunday())
//            mealPlansDBSource.deleteAllNutrients(mealPlansDBSource.getAllNutrients())
        }
    }

    override suspend fun generateDayTemplate(
        day: String
    ): GenerateTemplateData {
        val nutrientsList = currentPlanDBSource.getAllNutrients()
        return when (day) {
            "monday" -> GenerateTemplateData(
                templateEntityMapper.mondayMealsMapper(currentPlanDBSource.getAllMonday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[0])
            )
            "tuesday" -> GenerateTemplateData(
                templateEntityMapper.tuesdayMealsMapper(currentPlanDBSource.getAllTuesday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[1])
            )
            "wednesday" -> GenerateTemplateData(
                templateEntityMapper.wednesdayMealsMapper(currentPlanDBSource.getAllWednesday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[2])
            )
            "thursday" -> GenerateTemplateData(
                templateEntityMapper.thursdayMealsMapper(currentPlanDBSource.getAllThursday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[3])
            )
            "friday" -> GenerateTemplateData(
                templateEntityMapper.fridayMealsMapper(currentPlanDBSource.getAllFriday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[4])
            )
            "saturday" -> GenerateTemplateData(
                templateEntityMapper.saturdayMealsMapper(currentPlanDBSource.getAllSaturday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[5])
            )
            "sunday" -> GenerateTemplateData(
                templateEntityMapper.sundayMealsMapper(currentPlanDBSource.getAllSunday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[6])
            )
            else -> GenerateTemplateData(
                templateEntityMapper.mondayMealsMapper(currentPlanDBSource.getAllMonday()),
                templateEntityMapper.nutrientsMapper(nutrientsList[0])
            )
        }
    }

    override suspend fun addPlanToDB(plan: String) {
        withContext(Dispatchers.IO) {
            mealPlansDBSource.deleteMonday(plan)
            mealPlansDBSource.deleteTuesday(plan)
            mealPlansDBSource.deleteWednesday(plan)
            mealPlansDBSource.deleteThursday(plan)
            mealPlansDBSource.deleteFriday(plan)
            mealPlansDBSource.deleteSaturday(plan)
            mealPlansDBSource.deleteSunday(plan)
            mealPlansDBSource.deleteNutrients(plan)

            mealPlansDBSource.insertAllMonday(
                currentToMealPlansMapper.toMondayEntity(
                    currentPlanDBSource.getAllMonday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllTuesday(
                currentToMealPlansMapper.toTuesdayEntity(
                    currentPlanDBSource.getAllTuesday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllWednesday(
                currentToMealPlansMapper.toWednesdayEntity(
                    currentPlanDBSource.getAllWednesday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllThursday(
                currentToMealPlansMapper.toThursdayEntity(
                    currentPlanDBSource.getAllThursday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllFriday(
                currentToMealPlansMapper.toFridayEntity(
                    currentPlanDBSource.getAllFriday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllSaturday(
                currentToMealPlansMapper.toSaturdayEntity(
                    currentPlanDBSource.getAllSaturday(),
                    plan
                )
            )
            mealPlansDBSource.insertAllSunday(
                currentToMealPlansMapper.toSundayEntity(
                    currentPlanDBSource.getAllSunday(),
                    plan
                )
            )
            currentPlanDBSource.getAllNutrients().map {
                mealPlansDBSource.insertAllNutrients(
                    currentToMealPlansMapper.toNutrientsEntity(
                        it,
                        plan
                    )
                )
            }
        }
    }

    override suspend fun getPlans(): List<String> {
        return withContext(Dispatchers.IO) {
            val plans = mutableListOf<String>()
            mealPlansDBSource.getMondayPlans().map {
                plans.add(it.plan)
            }
            plans
        }
    }

    override fun setToken(token: String) {
        userSource.setUserToken(token)
    }
}