package com.felippeneves.kotlin_compose_weather_app.presentation.features.search.events

sealed class SearchCityEvent {
    data class UpdateQuery(val query: String) : SearchCityEvent()
    data object ConfirmCity : SearchCityEvent()
}