package com.felippeneves.kotlin_compose_weather_app.domain.model

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
) {
    companion object {
        fun createToPreview(): Temp =
            Temp(
                day = 34.47,
                min = 20.0,
                max = 35.15
            )

        fun createToPreview2(): Temp =
            Temp(
                day = 14.92,
                min = 7.19,
                max = 15.6
            )
    }
}
