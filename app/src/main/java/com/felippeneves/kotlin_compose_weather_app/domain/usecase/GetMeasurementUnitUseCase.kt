package com.felippeneves.kotlin_compose_weather_app.domain.usecase

import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.core.utils.Constants
import com.felippeneves.kotlin_compose_weather_app.domain.repository.MeasurementUnitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetMeasurementUnitUseCase {
    suspend operator fun invoke(): Flow<DataResult<String>>
}

class GetMeasurementUnitUseCaseImpl @Inject constructor(
    private val repository: MeasurementUnitRepository
) : GetMeasurementUnitUseCase {
    override suspend fun invoke(): Flow<DataResult<String>> = flow {
            emit(DataResult.Loading)
            try {
                repository.getMeasurementUnit().map { it ?: Constants.DEFAULT_MEASUREMENT_UNIT }
                    .collect {
                        emit(DataResult.Success(it))
                    }
            } catch (e: Exception) {
                emit(DataResult.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
}