package com.felippeneves.kotlin_compose_weather_app.data.mapper

import com.felippeneves.kotlin_compose_weather_app.data.model.entity.FavoriteCityEntity
import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity

fun List<FavoriteCityEntity>.toFavoriteCityList() = map { it.toFavoriteCity() }

fun FavoriteCityEntity.toFavoriteCity() = FavoriteCity(
    city = city,
    country = country
)

fun List<FavoriteCity>.toFavoriteCityEntityList() = map { it.toFavoriteCityEntity() }

fun FavoriteCity.toFavoriteCityEntity() = FavoriteCityEntity(
    city = city,
    country = country
)
