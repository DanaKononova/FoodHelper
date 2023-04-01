package com.example.foodhelper.ui.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.foodhelper.ui.recipePage.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RecipeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    fun bindLoginViewModel(viewModel: RecipeViewModel): ViewModel
}