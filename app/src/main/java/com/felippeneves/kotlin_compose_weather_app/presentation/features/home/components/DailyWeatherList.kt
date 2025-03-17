package com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.kotlin_compose_weather_app.domain.model.WeatherItem

@Composable
fun DailyWeatherList(
    modifier: Modifier = Modifier,
    data: List<WeatherItem>,
    isImperial: Boolean
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color.White,
    ) {
        LazyColumn {
            items(items = data) { item: WeatherItem ->
                DailyWeatherItem(weather = item, isImperial = isImperial)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DailyWeatherList(
        data = WeatherItem.createListToPreview(),
        isImperial = false
    )
}
