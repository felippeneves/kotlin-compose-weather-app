package com.felippeneves.kotlin_compose_weather_app.domain.model

data class CurrentCity(
    val name: String = "",
    val isFavorite: Boolean = false
) {
    companion object {
        fun createToPreview() = CurrentCity(
            name = "São Paulo",
            isFavorite = true
        )
    }
}
