package com.felippeneves.kotlin_compose_weather_app.presentation.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.utils.DateUtil
import com.felippeneves.kotlin_compose_weather_app.core.utils.TemperatureFormatter
import com.felippeneves.kotlin_compose_weather_app.presentation.components.CityNotFoundComp
import com.felippeneves.kotlin_compose_weather_app.presentation.components.CustomToast
import com.felippeneves.kotlin_compose_weather_app.presentation.components.CustomTopAppBar
import com.felippeneves.kotlin_compose_weather_app.presentation.components.LoadingIndicator
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components.DailyWeatherList
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components.HumidityWindPressureRow
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components.SunsetSunriseRow
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components.WeatherStatusWidget
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.events.HomeEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.state.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeContent(
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit,
    toastEvent: SharedFlow<String>
) {
    val isImperial = TemperatureFormatter.isImperial(uiState.measurementUnit)
    val weather = uiState.weather
    var toastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        toastEvent.collect { message -> toastMessage = message }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = uiState.currentCity.name,
                showBackButton = false,
                showSearchButton = true,
                showFavoriteButton = true,
                isFavorite = uiState.currentCity.isFavorite,
                onFavoriteClick = { onEvent(HomeEvent.CheckedFavorite) }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {
            when {
                uiState.isLoading -> LoadingIndicator()

                weather == null -> CityNotFoundComp(errorMessage = uiState.errorMessage) {
                    onEvent(HomeEvent.RetryFetch)
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val currentWeather = weather.weeklyWeatherList[0]

                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = DateUtil.formatDate(currentWeather.timestampDate),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                        )

                        WeatherStatusWidget(weather = currentWeather, isImperial = isImperial)

                        HumidityWindPressureRow(weather = currentWeather, isImperial = isImperial)

                        HorizontalDivider()

                        SunsetSunriseRow(weather = currentWeather)

                        Text(
                            text = stringResource(R.string.this_week),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        DailyWeatherList(
                            data = weather.weeklyWeatherList,
                            isImperial = isImperial
                        )
                    }
                }
            }

            toastMessage?.let { message ->
                CustomToast(message) { toastMessage = null }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeContent(
        uiState = HomeState.createToPreview(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}

@Preview
@Composable
private fun EmptyPreview() {
    HomeContent(
        uiState = HomeState(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}
