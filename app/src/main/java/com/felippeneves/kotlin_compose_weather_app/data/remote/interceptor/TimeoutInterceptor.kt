package com.felippeneves.kotlin_compose_weather_app.data.remote.interceptor

import com.felippeneves.kotlin_compose_weather_app.core.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class TimeoutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: SocketTimeoutException) {
            throw NetworkException.Timeout
        } catch (e: IOException) {
            throw IOException("Network error occurred. Please check your connection.")
        }
    }
}