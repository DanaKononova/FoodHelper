package com.example.data.source

import com.example.data.db.food.FoodDao
import com.example.data.db.food.FoodEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodDataBaseSource @Inject constructor(
    private val foodDao: FoodDao
) {
    suspend fun getAll() = withContext(Dispatchers.IO) {
        foodDao.getAll()
    }

    suspend fun insertAll(news: List<FoodEntity>) = withContext(Dispatchers.IO) {
        foodDao.insertAll(news)
    }

    suspend fun delete(news: List<FoodEntity>) = withContext(Dispatchers.IO) {
        foodDao.delete(news)
    }
}