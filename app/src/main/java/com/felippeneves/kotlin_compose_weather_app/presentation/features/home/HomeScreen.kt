package com.felippeneves.kotlin_compose_weather_app.presentation.features.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.events.HomeEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.home.state.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeScreen(
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit,
    toastEvent: SharedFlow<String>
) {
    HomeContent(
        uiState = uiState,
        onEvent = onEvent,
        toastEvent = toastEvent
    )
}


@Preview
@Composable
private fun Preview() {
    HomeScreen(
        uiState = HomeState.createToPreview(),
        onEvent = {},
        toastEvent = MutableSharedFlow()
    )
}
