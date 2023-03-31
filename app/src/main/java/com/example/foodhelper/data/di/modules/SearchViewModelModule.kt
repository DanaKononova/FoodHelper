package com.example.foodhelper.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.foodhelper.data.di.ViewModelKey
import com.example.foodhelper.ui.searchPage.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindLoginViewModel(viewModel: SearchViewModel): ViewModel
}