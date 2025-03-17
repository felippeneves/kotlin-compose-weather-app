package com.felippeneves.kotlin_compose_weather_app.data.model.response

data class CityResponse(
    val coord: CoordResponse,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)