package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.response.CityResponse
import com.felippeneves.kotlin_compose_weather_app.domain.model.City

fun CityResponse.toCity() = City(
    name = name,
    country = country
)
