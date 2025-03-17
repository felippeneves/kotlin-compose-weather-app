package com.felippeneves.kotlin_compose_weather_app.presentation.features.settings.events

sealed class SettingsEvent {
    data class UpdateMeasurementUnit(val unit: String) : SettingsEvent()
    data object SaveMeasurementUnit : SettingsEvent()
    data object ShowMeasurementUnitListDialog : SettingsEvent()
    data object DismissMeasurementUnitListDialog : SettingsEvent()
    data object RetryFetch : SettingsEvent()
}