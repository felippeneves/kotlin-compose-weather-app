package com.felippeneves.kotlin_compose_weather_app.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.felippeneves.kotlin_compose_weather_app.core.exception.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtil @Inject constructor(@ApplicationContext private val context: Context) {

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    fun requireInternetConnection() {
        if (!isInternetAvailable()) {
            throw NetworkException.NoInternetConnection
        }
    }
}