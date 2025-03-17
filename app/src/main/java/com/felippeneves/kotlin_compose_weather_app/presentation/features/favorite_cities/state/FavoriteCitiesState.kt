package com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.state

import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity

data class FavoriteCitiesState(
    val favoriteCitiesList: List<FavoriteCity> = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val showConfirmDialog: Boolean = false,
    val cityToDelete: FavoriteCity? = null
) {
    companion object {
        fun createToPreview() = FavoriteCitiesState(
            favoriteCitiesList = FavoriteCity.createListToPreview(),
        )

        fun createToConfirmDeletePreview() = FavoriteCitiesState(
            favoriteCitiesList = FavoriteCity.createListToPreview(),
            showConfirmDialog = true,
            cityToDelete = FavoriteCity.createToPreview()
        )
    }
}
