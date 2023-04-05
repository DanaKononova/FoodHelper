package com.example.foodhelper.ui.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.foodhelper.ui.generate_plan_page.GeneratePlanViewModel
import com.example.foodhelper.ui.user_page.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GeneratePlanViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GeneratePlanViewModel::class)
    fun bindLoginViewModel(viewModel: GeneratePlanViewModel): ViewModel
}