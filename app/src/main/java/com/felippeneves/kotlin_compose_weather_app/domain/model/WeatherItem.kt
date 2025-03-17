package com.felippeneves.kotlin_compose_weather_app.domain.model

data class WeatherItem(
    val timestampDate: Int,
    val humidity: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val weatherObject: WeatherObject,
    val temp: Temp
) {
    companion object {
        fun createToPreview(): WeatherItem =
            WeatherItem(
                timestampDate = 1741446000,
                humidity = 32,
                pressure = 1013,
                speed = 3.61,
                sunrise = 1741597909,
                sunset = 1741642280,
                weatherObject = WeatherObject.createToPreview(),
                temp = Temp.createToPreview()
            )

        private fun createToPreview2(): WeatherItem =
            WeatherItem(
                timestampDate = 1741521600,
                humidity = 51,
                pressure = 1003,
                speed = 4.38,
                sunrise = 1741587952,
                sunset = 1741629353,
                weatherObject = WeatherObject.createToPreview2(),
                temp = Temp.createToPreview2()
            )

        fun createListToPreview(): List<WeatherItem> = listOf(
            createToPreview(),
            createToPreview2(),
            createToPreview(),
            createToPreview2(),
        )
    }
}
