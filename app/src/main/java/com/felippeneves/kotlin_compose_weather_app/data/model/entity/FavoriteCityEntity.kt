package com.felippeneves.kotlin_compose_weather_app.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_FAVORITE_CITY")
data class FavoriteCityEntity(
    @PrimaryKey
    @ColumnInfo(name = "CITY")
    val city: String,

    @ColumnInfo(name = "COUNTRY")
    val country: String,
)
