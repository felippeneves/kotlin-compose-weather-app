package com.felippeneves.kotlin_compose_weather_app.data.model.response

data class WeatherObjectResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)