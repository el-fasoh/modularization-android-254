package com.example.network

import com.example.domain.services.CovidStatsService
import com.example.network.api.CovidStatsApi
import com.example.network.service.CovidStatsServiceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkhttpClientToken(logging: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(logging) //Wrap in debug check
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl("https://api.covidtracking.com/v1/us/") //Don't be lazy here
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideCovidStatsService(covidStatsApi: CovidStatsApi): CovidStatsService = CovidStatsServiceImpl(covidStatsApi)

    @Provides
    @Singleton
    fun provideCovidStatsApi(retrofit: Retrofit): CovidStatsApi = retrofit.create(CovidStatsApi::class.java)
}