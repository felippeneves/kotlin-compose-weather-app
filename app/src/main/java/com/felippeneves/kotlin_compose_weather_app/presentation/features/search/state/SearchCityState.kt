package com.felippeneves.kotlin_compose_weather_app.presentation.features.search.state

data class SearchCityState(
    val query: String = "",
    val isLoading: Boolean = false,
) {
    companion object {
        fun createToPreview() = SearchCityState(
            query = "Test value"
        )
    }
}
