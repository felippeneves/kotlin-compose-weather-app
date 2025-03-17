package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.response.WeatherItemResponse
import com.felippeneves.kotlin_compose_weather_app.domain.model.WeatherItem

fun List<WeatherItemResponse>.toWeatherItemList() = map { it.toWeatherItem() }

fun WeatherItemResponse.toWeatherItem() = WeatherItem(
    timestampDate = dt,
    humidity = humidity,
    pressure = pressure,
    speed = speed,
    sunrise = sunrise,
    sunset = sunset,
    weatherObject = weather[0].toWeatherObject(),
    temp = temp.toTemp()
)
