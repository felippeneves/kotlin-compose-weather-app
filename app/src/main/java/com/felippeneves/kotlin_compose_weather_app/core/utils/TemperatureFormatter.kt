package com.felippeneves.kotlin_compose_weather_app.core.utils

import android.content.Context
import com.felippeneves.kotlin_compose_weather_app.R

object TemperatureFormatter {
    fun format(temp: Double, isImperial: Boolean): String =
        DecimalUtil.format(temp) + "°" + if (isImperial) "F" else "C"

    fun getUnitLabel(unit: String?, context: Context): String =
        when (unit) {
            Constants.IMPERIAL_MEASUREMENT_UNIT -> context.getString(R.string.fahrenheit_f)
            Constants.METRIC_MEASUREMENT_UNIT -> context.getString(R.string.celsius_c)
            else -> context.getString(R.string.chose_unit)
        }

    fun isImperial(unit: String): Boolean = unit == Constants.IMPERIAL_MEASUREMENT_UNIT
}
