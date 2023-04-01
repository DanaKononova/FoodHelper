package com.example.foodhelper.ui.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.foodhelper.ui.mainPage.FoodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel::class)
    fun bindLoginViewModel(viewModel: FoodViewModel): ViewModel
}