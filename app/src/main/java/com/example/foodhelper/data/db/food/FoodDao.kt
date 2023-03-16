package com.example.foodhelper.data.db.food

import androidx.room.*

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): List<FoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<FoodEntity>)

    @Delete
    fun delete(news: List<FoodEntity>)
}