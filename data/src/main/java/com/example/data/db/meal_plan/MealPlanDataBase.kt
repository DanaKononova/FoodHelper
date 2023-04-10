package com.example.data.db.meal_plan

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MondayEntity::class, TuesdayEntity::class, WednesdayEntity::class, ThursdayEntity::class, FridayEntity::class, SaturdayEntity::class, SundayEntity::class, NutrientsEntity::class],
    version = 1
)
abstract class MealPlanDataBase : RoomDatabase() {
    abstract fun mealPlanDao(): MealPlanDao
}