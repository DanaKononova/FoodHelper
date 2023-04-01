package com.example.foodhelper.ui

import android.app.Application
import com.example.foodhelper.ui.di.AppComponent
import com.example.foodhelper.ui.di.DaggerAppComponent

class FoodApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}