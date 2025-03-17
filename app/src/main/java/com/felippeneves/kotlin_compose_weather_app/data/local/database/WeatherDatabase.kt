package com.felippeneves.kotlin_compose_weather_app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felippeneves.kotlin_compose_weather_app.data.model.entity.FavoriteCityEntity

@Database(
    entities = [
        FavoriteCityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun FavoriteCityDao(): FavoriteCityDao
}
