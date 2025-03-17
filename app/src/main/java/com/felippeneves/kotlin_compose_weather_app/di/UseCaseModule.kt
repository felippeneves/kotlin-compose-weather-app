package com.felippeneves.kotlin_compose_weather_app.di

import com.felippeneves.kotlin_compose_weather_app.domain.repository.FavoriteCityRepository
import com.felippeneves.kotlin_compose_weather_app.domain.repository.MeasurementUnitRepository
import com.felippeneves.kotlin_compose_weather_app.domain.repository.WeatherRepository
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.AddFavoriteCityUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.AddFavoriteCityUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.DeleteFavoriteCityByIdUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.DeleteFavoriteCityByIdUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetFavoriteCitiesUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetFavoriteCitiesUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetMeasurementUnitUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetMeasurementUnitUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetWeatherByCityUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetWeatherByCityUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetWeatherSettingsUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetWeatherSettingsUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveCurrentCityUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveCurrentCityUseCaseImpl
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveMeasurementUnitUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveMeasurementUnitUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetWeatherByCityUseCase(repository: WeatherRepository): GetWeatherByCityUseCase =
        GetWeatherByCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetFavoriteCitiesUseCase(repository: FavoriteCityRepository): GetFavoriteCitiesUseCase =
        GetFavoriteCitiesUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideDeleteFavoriteCityByIdUseCase(repository: FavoriteCityRepository): DeleteFavoriteCityByIdUseCase =
        DeleteFavoriteCityByIdUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetWeatherSettingsUseCase(
        favoriteCityRepository: FavoriteCityRepository,
        measurementUnitRepository: MeasurementUnitRepository
    ): GetWeatherSettingsUseCase = GetWeatherSettingsUseCaseImpl(
        favoriteCityRepository = favoriteCityRepository,
        measurementUnitRepository = measurementUnitRepository
    )

    @Singleton
    @Provides
    fun provideAddFavoriteCityUseCase(repository: FavoriteCityRepository): AddFavoriteCityUseCase =
        AddFavoriteCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideSaveCurrentCityUseCase(repository: FavoriteCityRepository): SaveCurrentCityUseCase =
        SaveCurrentCityUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideSaveMeasurementUnitUseCase(repository: MeasurementUnitRepository): SaveMeasurementUnitUseCase =
        SaveMeasurementUnitUseCaseImpl(repository = repository)

    @Singleton
    @Provides
    fun provideGetMeasurementUnitUseCase(repository: MeasurementUnitRepository): GetMeasurementUnitUseCase =
        GetMeasurementUnitUseCaseImpl(repository = repository)
}
