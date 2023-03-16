package com.example.foodhelper.data.di.modules

import com.example.foodhelper.data.RepositoryImpl
import com.example.foodhelper.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getRepository(impl: RepositoryImpl): Repository
}