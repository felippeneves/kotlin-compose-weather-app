package com.felippeneves.kotlin_compose_weather_app.presentation.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.core.ResourceProvider
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetMeasurementUnitUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveMeasurementUnitUseCase
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.events.SettingsEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.state.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getMeasurementUnitUseCase: GetMeasurementUnitUseCase,
    private val saveMeasurementUnitUseCase: SaveMeasurementUnitUseCase
) : ViewModel() {

    // region states

    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    private val _finishScreen = MutableSharedFlow<Unit>()
    val finishScreen: SharedFlow<Unit> = _finishScreen

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    // endregion states

    init {
        fetchItem()
    }

    private fun fetchItem() = viewModelScope.launch {
        getMeasurementUnitUseCase.invoke().collectLatest { result ->
            when (result) {
                is DataResult.Success -> _uiState.update {
                    it.copy(
                        measurementUnit = result.data!!,
                        isLoading = false
                    )
                }

                is DataResult.Failure -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.e?.message.toString()
                    )
                }

                DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.ShowMeasurementUnitListDialog -> _uiState.update {
                it.copy(showMeasurementUnitListDialog = true)
            }

            is SettingsEvent.UpdateMeasurementUnit -> {
                _uiState.update {
                    it.copy(
                        measurementUnit = event.unit,
                        showMeasurementUnitListDialog = false
                    )
                }
            }

            is SettingsEvent.SaveMeasurementUnit -> {
                saveMeasurementUnit()
                _uiState.update { it.copy(showMeasurementUnitListDialog = false) }
            }

            SettingsEvent.DismissMeasurementUnitListDialog -> _uiState.update {
                it.copy(showMeasurementUnitListDialog = false)
            }

            SettingsEvent.RetryFetch -> retryFetchItem()
        }
    }

    private fun saveMeasurementUnit() = viewModelScope.launch {
        val measurementUnit = _uiState.value.measurementUnit

        saveMeasurementUnitUseCase.invoke(params = SaveMeasurementUnitUseCase.Params(measurementUnit))
            .collectLatest { result ->
                when (result) {
                    is DataResult.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _toastEvent.emit(resourceProvider.getString(R.string.measurement_unit_saved_successfully))
                        _finishScreen.emit(Unit)
                    }

                    is DataResult.Failure -> {
                        _uiState.update { it.copy(isLoading = false) }
                        _toastEvent.emit(resourceProvider.getString(R.string.error_saving_measurement_unit))
                    }

                    DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                }
            }
    }

    private fun retryFetchItem() {
        _uiState.update {
            it.copy(
                errorMessage = "",
                showMeasurementUnitListDialog = false
            )
        }.apply {
            fetchItem()
        }
    }
}
