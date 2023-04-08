package com.example.foodhelper.ui.di.modules

import com.example.data.network.*
import com.example.domain.models.generate_template.GenerateTemplateData
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
        .baseUrl("https://api.spoonacular.com/")
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
    fun getContactUserService(retrofit: Retrofit): ContactUserService =
        retrofit.create(ContactUserService::class.java)

    @Provides
    @Singleton
    fun getMealTemplatesService(retrofit: Retrofit): MealTemplatesService =
        retrofit.create(MealTemplatesService::class.java)

    @Provides
    @Singleton
    fun getGenerateTemplateService(retrofit: Retrofit): GenerateTemplateService =
        retrofit.create(GenerateTemplateService::class.java)

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}