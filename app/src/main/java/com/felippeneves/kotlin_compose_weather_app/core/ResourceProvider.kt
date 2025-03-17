package com.felippeneves.kotlin_compose_weather_app.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)
}