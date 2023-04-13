package com.example.foodhelper.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.db.food.FoodDao
import com.example.data.db.food.FoodDataBase
import com.example.data.db.instructions.InstructionsDao
import com.example.data.db.instructions.InstructionsDataBase
import com.example.data.db.current_plan.CurrentPlanDao
import com.example.data.db.current_plan.CurrentPlanDataBase
import com.example.data.db.meal_plans.MealPlansDao
import com.example.data.db.meal_plans.MealPlansDataBase
import com.example.data.db.nutrition.NutritionDao
import com.example.data.db.nutrition.NutritionDataBase
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
    fun provideCurrentPlanDataBase(context: Context): CurrentPlanDataBase {
        return Room.databaseBuilder(context, CurrentPlanDataBase::class.java, "CurrentPlanList")
            .build()
    }

    @Provides
    @Singleton
    fun provideMealPlansDataBase(context: Context): MealPlansDataBase {
        return Room.databaseBuilder(context, MealPlansDataBase::class.java, "MealPlansList")
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

    @Provides
    @Singleton
    fun provideCurrentPlanDao(db: CurrentPlanDataBase): CurrentPlanDao = db.currentPlanDao()

    @Provides
    @Singleton
    fun provideMealPlansDao(db: MealPlansDataBase): MealPlansDao = db.mealPlansDao()
}