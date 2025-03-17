package com.felippeneves.kotlin_compose_weather_app.domain.repository

import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity
import kotlinx.coroutines.flow.Flow

interface FavoriteCityRepository {
    suspend fun getAll(): Flow<List<FavoriteCity>>
    suspend fun save(favoriteCity: FavoriteCity)
    suspend fun deleteAll()
    suspend fun deleteById(city: String)
    suspend fun saveCurrentCity(city: String)
    suspend fun getCurrentCity(): Flow<String?>
    suspend fun isFavorite(city: String): Flow<Boolean>
}
