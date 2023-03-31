package com.example.foodhelper.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.foodhelper.data.di.ViewModelKey
import com.example.foodhelper.ui.nutritionPage.NutritionViewModel
import com.example.foodhelper.ui.recipePage.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NutritionViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NutritionViewModel::class)
    fun bindLoginViewModel(viewModel: NutritionViewModel): ViewModel
}