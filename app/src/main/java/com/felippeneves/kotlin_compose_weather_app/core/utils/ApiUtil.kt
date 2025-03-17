package com.felippeneves.kotlin_compose_weather_app.core.utils

object ApiUtil {

    // region Base URL to replace

    const val BASE_URL = "https://api.openweathermap.org"
    private const val BASE_IMAGE_URL = "https://openweathermap.org"

    // endregion Base URL to replace

    // region Endpoints

    const val PREFIX_API_DATA = "data"
    const val FORECAST_DAILY_API = "forecast/daily"

    // endregion Endpoints

    // region Version

    const val V2_5 = "2.5"

    // endregion Version

    // region Query Parameters

    const val QUERY_API_PARAM = "q"
    const val UNITS_API_PARAM = "units"
    const val APP_ID_API_PARAM = "APPID"

    // endregion Query Parameters

    // region Query Parameters Value

    const val UNITS_API_PARAM_DEFAULT_VALUE = "metric"

    // endregion Query Parameters Value

    fun buildIconUrl(icon: String): String = "${BASE_IMAGE_URL}/img/wn/${icon}.png"
}