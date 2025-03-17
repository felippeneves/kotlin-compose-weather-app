package com.felippeneves.kotlin_compose_weather_app.di

import com.felippeneves.kotlin_compose_weather_app.core.utils.NetworkUtil
import com.felippeneves.kotlin_compose_weather_app.data.local.data_store.DataStoreManager
import com.felippeneves.kotlin_compose_weather_app.data.local.database.FavoriteCityDao
import com.felippeneves.kotlin_compose_weather_app.data.remote.api.WeatherService
import com.felippeneves.kotlin_compose_weather_app.data.repository.FavoriteCityRepositoryImpl
import com.felippeneves.kotlin_compose_weather_app.data.repository.MeasurementUnitRepositoryImpl
import com.felippeneves.kotlin_compose_weather_app.data.repository.WeatherRepositoryImpl
import com.felippeneves.kotlin_compose_weather_app.domain.repository.FavoriteCityRepository
import com.felippeneves.kotlin_compose_weather_app.domain.repository.MeasurementUnitRepository
import com.felippeneves.kotlin_compose_weather_app.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        networkUtil: NetworkUtil,
        service: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(
        networkUtil = networkUtil,
        service = service
    )

    @Singleton
    @Provides
    fun provideFavoriteCityRepository(
        dao: FavoriteCityDao,
        dataStoreManager: DataStoreManager
    ): FavoriteCityRepository =
        FavoriteCityRepositoryImpl(
            dao = dao,
            dataStoreManager = dataStoreManager
        )

    @Singleton
    @Provides
    fun provideMeasurementUnitRepository(dataStoreManager: DataStoreManager): MeasurementUnitRepository =
        MeasurementUnitRepositoryImpl(dataStoreManager = dataStoreManager)
}
