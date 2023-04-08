package com.example.data.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lunch_table")
data class LunchEntity(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "imageType") val imageType: String
)