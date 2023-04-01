package com.example.foodhelper.ui.di

import android.content.Context
import com.example.foodhelper.ui.di.modules.*
import com.example.foodhelper.ui.mainPage.FoodMainFragment
import com.example.foodhelper.ui.nutritionPage.NutritionFragment
import com.example.foodhelper.ui.recipePage.RecipeFragment
import com.example.foodhelper.ui.searchPage.SearchFoodFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, ViewModelModule::class,
        SearchViewModelModule::class, RecipeViewModelModule::class, NutritionViewModelModule::class,
        SourceModule::class, DataBaseModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: FoodMainFragment)

    fun inject(fragment: SearchFoodFragment)

    fun inject(fragment: RecipeFragment)

    fun inject(fragment: NutritionFragment)

}