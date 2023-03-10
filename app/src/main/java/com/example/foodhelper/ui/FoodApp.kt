package com.example.foodhelper.ui

import android.app.Application
import com.example.foodhelper.data.di.AppComponent
import com.example.foodhelper.data.di.DaggerAppComponent

class FoodApp: Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }
}