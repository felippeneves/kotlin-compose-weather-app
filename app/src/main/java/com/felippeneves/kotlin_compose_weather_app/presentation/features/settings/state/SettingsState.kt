package com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.state

import com.felippeneves.kotlin_compose_weather_app.core.utils.Constants

data class SettingsState(
    val measurementUnit: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val showMeasurementUnitListDialog: Boolean = false
) {
    companion object {
        fun createToPreview() = SettingsState(
            measurementUnit = Constants.METRIC_MEASUREMENT_UNIT
        )
    }
}
