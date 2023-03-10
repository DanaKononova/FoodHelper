package com.example.foodhelper.data.di.modules

import com.example.foodhelper.data.network.AnalyzedInstructionService
import com.example.foodhelper.data.network.FoodService
import com.example.foodhelper.data.network.NutritionService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/recipes/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun getFoodService(retrofit: Retrofit): FoodService = retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun getNutritionService(retrofit: Retrofit): NutritionService =
        retrofit.create(NutritionService::class.java)

    @Provides
    @Singleton
    fun getInstructionsService(retrofit: Retrofit): AnalyzedInstructionService =
        retrofit.create(AnalyzedInstructionService::class.java)

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}