package com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity

@Composable
fun FavoriteCitiesList(
    modifier: Modifier = Modifier,
    data: List<FavoriteCity>,
    onDeleteItem: (FavoriteCity) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.White,
    ) {
        LazyColumn {
            items(items = data) { item: FavoriteCity ->
                FavoriteCityItem(favoriteCity = item, onDeleteItem = onDeleteItem)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavoriteCitiesList(
        data = FavoriteCity.createListToPreview(),
        onDeleteItem = {}
    )
}
