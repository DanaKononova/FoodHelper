package com.example.foodhelper.data.source

import com.example.foodhelper.data.db.nutrition.NutritionDao
import com.example.foodhelper.data.db.nutrition.NutritionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NutritionDataBaseSource @Inject constructor(
    private val nutritionDao: NutritionDao
) {
    suspend fun getAll() = withContext(Dispatchers.IO) {
        nutritionDao.getAll()
    }

    suspend fun insertAll(news: List<NutritionEntity>) = withContext(Dispatchers.IO) {
        nutritionDao.insertAll(news)
    }

    suspend fun delete(news: List<NutritionEntity>) = withContext(Dispatchers.IO) {
        nutritionDao.delete(news)
    }
}