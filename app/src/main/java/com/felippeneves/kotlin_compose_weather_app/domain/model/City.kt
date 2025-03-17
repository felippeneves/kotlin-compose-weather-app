package com.felippeneves.kotlin_compose_weather_app.domain.model

data class City(
    val name: String,
    val country: String,
) {
    companion object {
        fun createToPreview() = City(
            name = "Ribeirão Preto",
            country = "BR"
        )
    }
}
