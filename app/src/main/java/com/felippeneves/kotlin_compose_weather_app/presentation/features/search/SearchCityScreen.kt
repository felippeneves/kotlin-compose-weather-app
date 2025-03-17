package com.felippeneves.kotlin_compose_weather_app.presentation.features.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.kotlin_compose_weather_app.presentation.features.search.events.SearchCityEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.search.state.SearchCityState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SearchCityScreen(
    uiState: SearchCityState,
    onEvent: (SearchCityEvent) -> Unit,
    finishScreen: SharedFlow<Unit>,
    toastEvent: SharedFlow<String>
) {
    SearchCityContent(
        uiState = uiState,
        onEvent = onEvent,
        finishScreen = finishScreen,
        toastEvent = toastEvent
    )
}

@Preview
@Composable
private fun Preview() {
    SearchCityScreen(
        uiState = SearchCityState.createToPreview(),
        onEvent = {},
        finishScreen = MutableSharedFlow(),
        toastEvent = MutableSharedFlow()
    )
}
