package com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity
import com.felippeneves.kotlin_compose_weather_app.ui.theme.LightCyanGray
import com.felippeneves.kotlin_compose_weather_app.ui.theme.LightTeal

@Composable
fun FavoriteCityItem(
    modifier: Modifier = Modifier,
    favoriteCity: FavoriteCity,
    onDeleteItem: (FavoriteCity) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 8.dp),
        shape = CircleShape,
        color = LightTeal,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = favoriteCity.city,
                modifier = Modifier.padding(start = 8.dp)
            )

            Surface(
                shape = CircleShape,
                color = LightCyanGray
            ) {
                Text(
                    text = favoriteCity.country,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp),
                )
            }

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        onDeleteItem(favoriteCity)
                    },
                tint = Color.Red
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FavoriteCityItem(
        favoriteCity = FavoriteCity.createToPreview(),
        onDeleteItem = {}
    )
}
