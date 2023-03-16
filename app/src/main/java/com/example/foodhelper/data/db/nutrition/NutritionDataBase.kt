package com.example.foodhelper.data.db.nutrition

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NutritionEntity::class], version = 1)
abstract class NutritionDataBase : RoomDatabase() {
    abstract fun nutritionDao(): NutritionDao
}