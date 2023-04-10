package com.example.data.db.meal_plan

import androidx.room.*

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM monday_table")
    fun getAllMonday(): List<MondayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMonday(news: List<MondayEntity>)

    @Delete
    fun deleteAllMonday(news: List<MondayEntity>)

    @Query("SELECT * FROM tuesday_table")
    fun getAllTuesday(): List<TuesdayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTuesday(news: List<TuesdayEntity>)

    @Delete
    fun deleteAllTuesday(news: List<TuesdayEntity>)

    @Query("SELECT * FROM wednesday_table")
    fun getAllWednesday(): List<WednesdayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWednesday(news: List<WednesdayEntity>)

    @Delete
    fun deleteAllWednesday(news: List<WednesdayEntity>)

    @Query("SELECT * FROM thursday_table")
    fun getAllThursday(): List<ThursdayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllThursday(news: List<ThursdayEntity>)

    @Delete
    fun deleteAllThursday(news: List<ThursdayEntity>)

    @Query("SELECT * FROM friday_table")
    fun getAllFriday(): List<FridayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFriday(news: List<FridayEntity>)

    @Delete
    fun deleteAllFriday(news: List<FridayEntity>)

    @Query("SELECT * FROM saturday_table")
    fun getAllSaturday(): List<SaturdayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSaturday(news: List<SaturdayEntity>)

    @Delete
    fun deleteAllSaturday(news: List<SaturdayEntity>)

    @Query("SELECT * FROM sunday_table")
    fun getAllSunday(): List<SundayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSunday(news: List<SundayEntity>)

    @Delete
    fun deleteAllSunday(news: List<SundayEntity>)

    @Query("SELECT * FROM nutrients_table")
    fun getAllNutrients(): List<NutrientsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNutrients(news: NutrientsEntity)

    @Delete
    fun deleteAllNutrients(news: List<NutrientsEntity>)

}