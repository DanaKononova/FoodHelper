package com.example.foodhelper.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.foodhelper.data.db.food.FoodDao
import com.example.foodhelper.data.db.food.FoodDataBase
import com.example.foodhelper.data.db.instructions.InstructionsDao
import com.example.foodhelper.data.db.instructions.InstructionsDataBase
import com.example.foodhelper.data.db.nutrition.NutritionDao
import com.example.foodhelper.data.db.nutrition.NutritionDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideFoodDataBase(context: Context): FoodDataBase {
        return Room.databaseBuilder(context, FoodDataBase::class.java, "FoodList")
            .build()
    }

    @Provides
    @Singleton
    fun provideNutritionDataBase(context: Context): NutritionDataBase {
        return Room.databaseBuilder(context, NutritionDataBase::class.java, "NutritionList")
            .build()
    }

    @Provides
    @Singleton
    fun provideInstructionsDataBase(context: Context): InstructionsDataBase {
        return Room.databaseBuilder(context, InstructionsDataBase::class.java, "InstructionsList")
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(db: FoodDataBase): FoodDao = db.foodDao()

    @Provides
    @Singleton
    fun provideNutritionDao(db: NutritionDataBase): NutritionDao = db.nutritionDao()

    @Provides
    @Singleton
    fun provideInstructionsDao(db: InstructionsDataBase): InstructionsDao = db.instructionsDao()
}