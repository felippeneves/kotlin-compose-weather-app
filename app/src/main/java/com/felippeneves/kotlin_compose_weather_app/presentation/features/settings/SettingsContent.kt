package com.felippeneves.kotlin_compose_weather_app.presentation.features.settings

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.utils.TemperatureFormatter
import com.felippeneves.kotlin_compose_weather_app.presentation.components.CustomToast
import com.felippeneves.kotlin_compose_weather_app.presentation.components.CustomTopAppBar
import com.felippeneves.kotlin_compose_weather_app.presentation.components.ErrorComp
import com.felippeneves.kotlin_compose_weather_app.presentation.components.LoadingIndicator
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.components.MeasurementUnitListDialog
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.events.SettingsEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.state.SettingsState
import com.felippeneves.kotlin_compose_weather_app.presentation.navigation.NavigationManager
import com.felippeneves.kotlin_compose_weather_app.ui.theme.Amber800
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun SettingsContent(
    uiState: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    finishScreen: SharedFlow<Unit>,
    toastEvent: SharedFlow<String>
) {
    var toastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        launch { toastEvent.collect { message -> toastMessage = message } }
        launch { finishScreen.collect { NavigationManager.navigateBack() } }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = stringResource(R.string.settings))
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {
            when {
                uiState.errorMessage.isNotEmpty() -> ErrorComp(errorMessage = uiState.errorMessage) {
                    onEvent(SettingsEvent.RetryFetch)
                }

                uiState.isLoading -> LoadingIndicator()

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.change_units),
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = LocalIndication.current
                                ) { onEvent(SettingsEvent.ShowMeasurementUnitListDialog) },
                            shape = RectangleShape,
                            color = Amber800
                        ) {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = TemperatureFormatter.getUnitLabel(
                                    uiState.measurementUnit,
                                    LocalContext.current
                                ),
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Amber800,
                                contentColor = Color.White
                            ),
                            onClick = {
                                onEvent(SettingsEvent.SaveMeasurementUnit)
                            },
                        ) {
                            Text(
                                text = stringResource(R.string.save),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            uiState.showMeasurementUnitListDialog.takeIf { it }?.let {
                MeasurementUnitListDialog(
                    onConfirm = { unit -> onEvent(SettingsEvent.UpdateMeasurementUnit(unit)) },
                    onDismiss = { onEvent(SettingsEvent.DismissMeasurementUnitListDialog) },
                )
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
    SettingsContent(
        uiState = SettingsState.createToPreview(),
        onEvent = {},
        finishScreen = MutableSharedFlow(),
        toastEvent = MutableSharedFlow()
    )
}
