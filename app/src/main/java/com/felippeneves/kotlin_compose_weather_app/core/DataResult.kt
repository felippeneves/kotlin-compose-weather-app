package com.felippeneves.kotlin_compose_weather_app.core

sealed class DataResult<out T> {
    data object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T?) : DataResult<T>()
    data class Failure(val e: Exception?) : DataResult<Nothing>()
}
