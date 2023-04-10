package com.example.data.db.meal_plan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nutrients_table")
data class NutrientsEntity(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    @ColumnInfo("calories") val calories: Double,
    @ColumnInfo("carbohydrates") val carbohydrates: Double,
    @ColumnInfo("fat") val fat: Double,
    @ColumnInfo("protein") val protein: Double
)