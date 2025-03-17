package com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.events

import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity

sealed class FavoriteCitiesEvent {
    data class RemoveFavorite(val city: String) : FavoriteCitiesEvent()
    data class ShowDeleteDialog(val favoriteCity: FavoriteCity) : FavoriteCitiesEvent()
    data object DismissDeleteDialog : FavoriteCitiesEvent()
    data object RetryFetch : FavoriteCitiesEvent()
}
