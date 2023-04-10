package com.example.data.source

import com.example.data.db.food.*
import com.example.data.db.meal_plan.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealPlanDataBaseSource @Inject constructor(
    private val mealPlanDao: MealPlanDao
) {
    suspend fun getAllMonday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllMonday()
    }

    suspend fun insertAllMonday(news: List<MondayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllMonday(news)
    }

    suspend fun deleteAllMonday(news: List<MondayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllMonday(news)
    }

    suspend fun getAllTuesday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllTuesday()
    }

    suspend fun insertAllTuesday(news: List<TuesdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllTuesday(news)
    }

    suspend fun deleteAllTuesday(news: List<TuesdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllTuesday(news)
    }

    suspend fun getAllWednesday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllWednesday()
    }

    suspend fun insertAllWednesday(news: List<WednesdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllWednesday(news)
    }

    suspend fun deleteAllWednesday(news: List<WednesdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllWednesday(news)
    }

    suspend fun getAllThursday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllThursday()
    }

    suspend fun insertAllThursday(news: List<ThursdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllThursday(news)
    }

    suspend fun deleteAllThursday(news: List<ThursdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllThursday(news)
    }

    suspend fun getAllFriday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllFriday()
    }

    suspend fun insertAllFriday(news: List<FridayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllFriday(news)
    }

    suspend fun deleteAllFriday(news: List<FridayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllFriday(news)
    }

    suspend fun getAllSaturday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllSaturday()
    }

    suspend fun insertAllSaturday(news: List<SaturdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllSaturday(news)
    }

    suspend fun deleteAllSaturday(news: List<SaturdayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllSaturday(news)
    }

    suspend fun getAllSunday() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllSunday()
    }

    suspend fun insertAllSunday(news: List<SundayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllSunday(news)
    }

    suspend fun deleteAllSunday(news: List<SundayEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllSunday(news)
    }

    suspend fun getAllNutrients() = withContext(Dispatchers.IO) {
        mealPlanDao.getAllNutrients()
    }

    suspend fun insertAllNutrients(news: NutrientsEntity) = withContext(Dispatchers.IO) {
        mealPlanDao.insertAllNutrients(news)
    }

    suspend fun deleteAllNutrients(news: List<NutrientsEntity>) = withContext(Dispatchers.IO) {
        mealPlanDao.deleteAllNutrients(news)
    }
}