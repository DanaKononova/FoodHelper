package com.example.foodhelper.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.foodhelper.data.di.ViewModelKey
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