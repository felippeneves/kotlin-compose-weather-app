package com.felippeneves.kotlin_compose_weather_app.domain.usecase

import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.domain.repository.FavoriteCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface DeleteFavoriteCityByIdUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(val city: String)
}

class DeleteFavoriteCityByIdUseCaseImpl(
    private val repository: FavoriteCityRepository
) : DeleteFavoriteCityByIdUseCase {
    override suspend fun invoke(params: DeleteFavoriteCityByIdUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                emit(DataResult.Loading)
                emit(DataResult.Success(repository.deleteById(params.city)))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
