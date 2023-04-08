package com.example.data.db.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_table")
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") var amount: String,
    @ColumnInfo(name = "percentOfDailyNeeds") val percentOfDailyNeeds: Float,
)