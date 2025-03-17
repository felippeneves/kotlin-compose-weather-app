package com.felippeneves.kotlin_compose_weather_app.data.repository

import com.felippeneves.kotlin_compose_weather_app.core.utils.NetworkUtil
import com.felippeneves.kotlin_compose_weather_app.data.mapper.toWeather
import com.felippeneves.kotlin_compose_weather_app.data.remote.api.WeatherService
import com.felippeneves.kotlin_compose_weather_app.domain.model.Weather
import com.felippeneves.kotlin_compose_weather_app.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    networkUtil: NetworkUtil,
    private val service: WeatherService
) : DataRepository(networkUtil), WeatherRepository {

    override suspend fun getWeatherByCity(
        query: String,
        units: String
    ): Weather? = fetchValidResponse(service.fetchWeatherByCity(query, units))?.toWeather()
}