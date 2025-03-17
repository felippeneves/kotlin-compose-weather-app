package com.felippeneves.kotlin_compose_weather_app.data.model.response

data class TempResponse(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)