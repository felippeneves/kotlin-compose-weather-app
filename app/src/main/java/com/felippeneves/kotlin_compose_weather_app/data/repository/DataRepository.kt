package com.felippeneves.kotlin_compose_weather_app.data.repository

import com.felippeneves.kotlin_compose_weather_app.core.exception.NetworkException
import com.felippeneves.kotlin_compose_weather_app.core.utils.NetworkUtil
import retrofit2.Response

abstract class DataRepository(private val networkUtil: NetworkUtil) {
    companion object {
        private const val STATUS_OK = 200
    }

    protected fun <T> fetchValidResponse(response: Response<T>): T? {
        networkUtil.requireInternetConnection()
        return extractResponseBody(response)
    }

    private fun <T> extractResponseBody(response: Response<T>): T? {
        val httpStatusCode = response.code()
        if (httpStatusCode == STATUS_OK) {
            return response.body()
        } else {
            throw NetworkException.ServerError(
                code = httpStatusCode,
                errorMessage = response.message()
            )
        }
    }
}