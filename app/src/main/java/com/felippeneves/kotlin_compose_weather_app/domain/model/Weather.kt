package com.felippeneves.kotlin_compose_weather_app.domain.model

data class Weather(
    val city: City,
    val weeklyWeatherList: List<WeatherItem>
) {
    companion object {
        fun createToPreview() = Weather(
            city = City.createToPreview(),
            weeklyWeatherList = WeatherItem.createListToPreview()
        )
    }
}
