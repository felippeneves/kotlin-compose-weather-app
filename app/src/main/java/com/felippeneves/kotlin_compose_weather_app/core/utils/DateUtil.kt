package com.felippeneves.kotlin_compose_weather_app.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    fun formatDate(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun formatDateTime(timestamp: Int): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun getAbbreviatedDayOfWeek(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun getDayOfWeek(timestamp: Int): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = Date(timestamp.toLong() * 1000)
        return sdf.format(date)
    }
}
