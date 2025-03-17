package com.felippeneves.kotlin_compose_weather_app.data.remote.api

import com.felippeneves.kotlin_compose_weather_app.BuildConfig
import com.felippeneves.kotlin_compose_weather_app.core.utils.ApiUtil
import com.felippeneves.kotlin_compose_weather_app.data.model.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherService {
    @GET("${ApiUtil.PREFIX_API_DATA}/${ApiUtil.V2_5}/${ApiUtil.FORECAST_DAILY_API}")
    suspend fun fetchWeatherByCity(
        @Query(ApiUtil.QUERY_API_PARAM) query: String,
        @Query(ApiUtil.UNITS_API_PARAM) units: String = ApiUtil.UNITS_API_PARAM_DEFAULT_VALUE,
        @Query(ApiUtil.APP_ID_API_PARAM) appId: String = BuildConfig.API_KEY
    ): Response<WeatherResponse>
}
