package com.felippeneves.kotlin_compose_weather_app.presentation.features.home.state

import com.felippeneves.kotlin_compose_weather_app.domain.model.CurrentCity
import com.felippeneves.kotlin_compose_weather_app.domain.model.Weather

data class HomeState(
    val weather: Weather? = null,
    val currentCity: CurrentCity = CurrentCity(),
    val measurementUnit: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
) {
    companion object {
        fun createToPreview() = HomeState(
            weather = Weather.createToPreview(),
            currentCity = CurrentCity.createToPreview()
        )
    }
}
