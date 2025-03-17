package com.felippeneves.kotlin_compose_weather_app.domain.usecase

import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SaveMeasurementUnitUseCase {
    suspend operator fun invoke(params: Params): Flow<DataResult<Unit>>
    data class Params(val measurementUnit: String)
}

class SaveMeasurementUnitUseCaseImpl @Inject constructor(
    private val repository: MeasurementUnitRepository
) : SaveMeasurementUnitUseCase {
    override suspend fun invoke(params: SaveMeasurementUnitUseCase.Params): Flow<DataResult<Unit>> =
        flow {
            try {
                emit(DataResult.Loading)
                val result = repository.saveMeasurementUnit(params.measurementUnit)
                emit(DataResult.Success(result))
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
