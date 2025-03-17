package com.felippeneves.kotlin_compose_weather_app.data.model.response

data class FeelsLikeResponse(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)