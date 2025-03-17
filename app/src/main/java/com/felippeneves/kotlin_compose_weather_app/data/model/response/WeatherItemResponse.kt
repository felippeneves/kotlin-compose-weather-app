package com.felippeneves.kotlin_compose_weather_app.data.model.response

import com.google.gson.annotations.SerializedName

data class WeatherItemResponse(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    @SerializedName("feels_like")
    val feelsLike: FeelsLikeResponse,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempResponse,
    val weather: List<WeatherObjectResponse>
)
