package com.example.foodhelper.ui.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.foodhelper.ui.user_page.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindLoginViewModel(viewModel: UserViewModel): ViewModel
}