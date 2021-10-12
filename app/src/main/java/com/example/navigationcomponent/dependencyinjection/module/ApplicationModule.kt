package com.example.navigationcomponent.dependencyinjection.module



import com.example.navigationcomponent.dependencyinjection.data.api.ApiHelper
import com.example.navigationcomponent.dependencyinjection.data.api.ApiHelperImpl
import com.example.navigationcomponent.dependencyinjection.data.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.lang.Boolean


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
    val BASE_URL:String = "https://5e510330f2c0d300147c034c.mockapi.io/"
    val DEBUG = Boolean.parseBoolean("true")

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

}