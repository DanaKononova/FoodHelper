package com.example.data.db.nutrition

import androidx.room.*

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition_table")
    fun getAll(): List<NutritionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NutritionEntity>)

    @Delete
    fun delete(news: List<NutritionEntity>)
}