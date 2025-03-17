package com.felippeneves.kotlin_compose_weather_app.domain.usecase

import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity
import com.felippeneves.kotlin_compose_weather_app.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetFavoriteCitiesUseCase {
    suspend operator fun invoke(): Flow<DataResult<List<FavoriteCity>>>
}

class GetFavoriteCitiesUseCaseImpl(
    private val repository: FavoriteCityRepository
) : GetFavoriteCitiesUseCase {
    override suspend fun invoke(): Flow<DataResult<List<FavoriteCity>>> = flow {
        try {
            emit(DataResult.Loading)
            repository.getAll().collect { result ->
                emit(DataResult.Success(result))
            }
        } catch (e: Exception) {
            emit(DataResult.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
