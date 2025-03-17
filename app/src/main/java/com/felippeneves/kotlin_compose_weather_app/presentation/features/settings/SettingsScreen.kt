package com.felippeneves.kotlin_compose_weather_app.presentation.features.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.events.SettingsEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.state.SettingsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


@Composable
fun SettingsScreen(
    uiState: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    finishScreen: SharedFlow<Unit>,
    toastEvent: SharedFlow<String>
) {
    SettingsContent(
        uiState = uiState,
        onEvent = onEvent,
        finishScreen = finishScreen,
        toastEvent = toastEvent
    )
}

@Preview
@Composable
private fun Preview() {
    SettingsScreen(
        uiState = SettingsState.createToPreview(),
        onEvent = {},
        finishScreen = MutableSharedFlow(),
        toastEvent = MutableSharedFlow()
    )
}
