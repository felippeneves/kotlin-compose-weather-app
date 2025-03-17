package com.felippeneves.kotlin_compose_weather_app.domain.model

data class WeatherObject(
    val id: Int,
    val description: String,
    val icon: String,
    val condition: String
) {
    companion object {
        fun createToPreview(): WeatherObject =
            WeatherObject(
                id = 804,
                description = "overcast clouds",
                icon = "04d",
                condition = "Clouds"
            )

        fun createToPreview2(): WeatherObject =
            WeatherObject(
                id = 804,
                description = "overcast clouds",
                icon = "04d",
                condition = "Clouds"
            )
    }
}
