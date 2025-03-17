package com.felippeneves.kotlin_compose_weather_app.domain.usecase

import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.domain.model.FavoriteCity
import com.felippeneves.kotlin_compose_weather_app.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AddFavoriteCityUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(val favoriteCity: FavoriteCity)
}

class AddFavoriteCityUseCaseImpl @Inject constructor(
    private val repository: FavoriteCityRepository
) : AddFavoriteCityUseCase {
    override suspend fun invoke(params: AddFavoriteCityUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                emit(DataResult.Loading)
                val result = repository.save(params.favoriteCity)
                emit(DataResult.Success(result))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
