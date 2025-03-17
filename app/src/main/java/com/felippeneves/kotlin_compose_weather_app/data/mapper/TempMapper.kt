package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.response.TempResponse
import com.felippeneves.kotlin_compose_weather_app.domain.model.Temp

fun TempResponse.toTemp() = Temp(
    day = day,
    min = min,
    max = max,
)
