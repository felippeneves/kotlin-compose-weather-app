package com.felippeneves.kotlin_compose_weather_app.core.exception

sealed class NetworkException(message: String) : Exception(message) {
    data object NoInternetConnection :
        NetworkException("You are offline. Please check your connection and try again.")

    data object Timeout : NetworkException("Request timed out. Please try again.")
    data class ServerError(val code: Int, val errorMessage: String) :
        NetworkException("Server error ($code): $errorMessage")
}
