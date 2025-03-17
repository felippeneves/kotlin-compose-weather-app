package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.response.WeatherResponse
import com.felippeneves.kotlin_compose_weather_app.domain.model.Weather

fun WeatherResponse.toWeather() = Weather(
    city = city.toCity(),
    weeklyWeatherList = list.toWeatherItemList(),
)
