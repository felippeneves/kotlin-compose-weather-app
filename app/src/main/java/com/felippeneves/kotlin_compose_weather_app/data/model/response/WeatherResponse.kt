package com.felippeneves.kotlin_compose_weather_app.data.model.response

data class WeatherResponse(
    val city: CityResponse,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItemResponse>,
)
