package com.example.foodhelper.data.db.food

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FoodEntity::class], version = 1)
abstract class FoodDataBase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}