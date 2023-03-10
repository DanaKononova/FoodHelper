package com.example.foodhelper.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.foodhelper.data.di.ViewModelKey
import com.example.foodhelper.ui.FoodViewModel
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