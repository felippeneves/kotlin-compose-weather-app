package com.felippeneves.kotlin_compose_weather_app.core.utils

object SpeedUtil {
    fun formatSpeed(speed: Double, isImperial: Boolean): String {
        val unit = if (isImperial) "mph" else "m/s"
        return "${DecimalUtil.format(speed)} $unit"
    }
}
