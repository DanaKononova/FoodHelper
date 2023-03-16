package com.example.foodhelper.data.di

import android.content.Context
import com.example.foodhelper.data.di.modules.*
import com.example.foodhelper.ui.FoodMainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, RepositoryModule::class, ViewModelModule::class, SourceModule::class, DataBaseModule::class]
)
interface AppComponent{
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: FoodMainFragment)
}