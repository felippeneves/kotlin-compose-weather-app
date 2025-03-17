package com.felippeneves.kotlin_compose_weather_app.presentation.features.home.events

sealed class HomeEvent {
    data object CheckedFavorite : HomeEvent()
    data object RetryFetch : HomeEvent()
}