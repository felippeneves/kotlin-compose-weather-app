package com.felippeneves.kotlin_compose_weather_app.di

import android.content.Context
import com.felippeneves.kotlin_compose_weather_app.BuildConfig
import com.felippeneves.kotlin_compose_weather_app.core.utils.ApiUtil
import com.felippeneves.kotlin_compose_weather_app.core.utils.NetworkUtil
import com.felippeneves.kotlin_compose_weather_app.data.remote.api.WeatherService
import com.felippeneves.kotlin_compose_weather_app.data.remote.interceptor.TimeoutInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideTimeoutInterceptor(): TimeoutInterceptor = TimeoutInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        timeoutInterceptor: TimeoutInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(timeoutInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiUtil.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkUtil(@ApplicationContext context: Context): NetworkUtil = NetworkUtil(context)

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}
