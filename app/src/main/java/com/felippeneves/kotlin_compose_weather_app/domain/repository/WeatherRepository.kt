package com.felippeneves.kotlin_compose_weather_app.domain.repository

import com.felippeneves.kotlin_compose_weather_app.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(
        query: String,
        units: String
    ): Weather?
}