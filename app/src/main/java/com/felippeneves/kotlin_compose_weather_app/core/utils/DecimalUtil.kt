package com.felippeneves.kotlin_compose_weather_app.core.utils

object DecimalUtil {
    fun format(value: Double): String {
        return "%.0f".format(value)
    }
}
