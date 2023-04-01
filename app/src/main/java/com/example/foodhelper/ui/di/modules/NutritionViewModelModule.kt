package com.example.foodhelper.ui.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.foodhelper.ui.nutritionPage.NutritionViewModel
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