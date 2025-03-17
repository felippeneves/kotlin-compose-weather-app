package com.felippeneves.kotlin_compose_weather_app.data.repository

import com.felippeneves.kotlin_compose_weather_app.data.local.data_store.DataStoreManager
import com.felippeneves.kotlin_compose_weather_app.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeasurementUnitRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : MeasurementUnitRepository {
    override suspend fun saveMeasurementUnit(measurementUnit: String) {
        dataStoreManager.saveCurrentMeasurementUnit(measurementUnit)
    }

    override suspend fun getMeasurementUnit(): Flow<String?> =
        dataStoreManager.currentMeasurementUnitFlow
}
