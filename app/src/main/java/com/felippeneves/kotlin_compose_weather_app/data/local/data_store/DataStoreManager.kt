package com.felippeneves.kotlin_compose_weather_app.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "app_prefs")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val CURRENT_CITY = stringPreferencesKey("current_city")
        val CURRENT_MEASUREMENT_UNIT = stringPreferencesKey("current_measurement_unit")
    }

    suspend fun saveCurrentCity(city: String) = context.dataStore.edit { prefs ->
        prefs[CURRENT_CITY] = city
    }

    val currentCityFlow: Flow<String?> = context.dataStore.data.map { prefs -> prefs[CURRENT_CITY] }


    suspend fun saveCurrentMeasurementUnit(measurementUnit: String) =
        context.dataStore.edit { prefs ->
            prefs[CURRENT_MEASUREMENT_UNIT] = measurementUnit
        }

    val currentMeasurementUnitFlow: Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[CURRENT_MEASUREMENT_UNIT] }
}
