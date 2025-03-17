package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.response.WeatherObjectResponse
import com.felippeneves.kotlin_compose_weather_app.domain.model.WeatherObject

fun WeatherObjectResponse.toWeatherObject() = WeatherObject(
    id = id,
    description = description,
    icon = icon,
    condition = main
)
