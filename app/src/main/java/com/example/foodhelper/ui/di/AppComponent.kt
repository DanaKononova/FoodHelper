package com.example.foodhelper.ui.di

import android.content.Context
import com.example.foodhelper.ui.di.modules.*
import com.example.foodhelper.ui.generate_plan_page.GeneratePlanFragment
import com.example.foodhelper.ui.main_page.FoodMainFragment
import com.example.foodhelper.ui.nutrition_page.NutritionFragment
import com.example.foodhelper.ui.recipe_page.RecipeFragment
import com.example.foodhelper.ui.search_page.SearchFoodFragment
import com.example.foodhelper.ui.user_page.UserFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, ViewModelModule::class,
        SearchViewModelModule::class, RecipeViewModelModule::class, NutritionViewModelModule::class,
        UserViewModelModule::class, GeneratePlanViewModelModule::class, SourceModule::class,
        DataBaseModule::class]
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

    fun inject(fragment: UserFragment)

    fun inject(fragment: GeneratePlanFragment)

}