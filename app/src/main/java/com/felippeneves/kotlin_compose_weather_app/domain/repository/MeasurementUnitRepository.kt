package com.felippeneves.kotlin_compose_weather_app.domain.repository

import kotlinx.coroutines.flow.Flow

interface MeasurementUnitRepository {
    suspend fun saveMeasurementUnit(measurementUnit: String)
    suspend fun getMeasurementUnit(): Flow<String?>
}