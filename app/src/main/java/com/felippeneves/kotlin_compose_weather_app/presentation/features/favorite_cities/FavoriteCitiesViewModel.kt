package com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.core.ResourceProvider
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.DeleteFavoriteCityByIdUseCase
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.GetFavoriteCitiesUseCase
import com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.events.FavoriteCitiesEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.favorite_cities.state.FavoriteCitiesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCitiesViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
    private val deleteFavoriteCityByIdUseCase: DeleteFavoriteCityByIdUseCase
) : ViewModel() {

    // region States

    private val _uiState = MutableStateFlow(FavoriteCitiesState())
    val uiState: StateFlow<FavoriteCitiesState> = _uiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    // endregion States

    init {
        fetchItems()
    }

    fun onEvent(event: FavoriteCitiesEvent) {
        when (event) {
            is FavoriteCitiesEvent.ShowDeleteDialog -> _uiState.update {
                it.copy(showConfirmDialog = true, cityToDelete = event.favoriteCity)
            }

            is FavoriteCitiesEvent.RemoveFavorite -> {
                deleteCity(event.city)
                _uiState.update { it.copy(showConfirmDialog = false, cityToDelete = null) }
            }

            FavoriteCitiesEvent.DismissDeleteDialog -> _uiState.update {
                it.copy(showConfirmDialog = false, cityToDelete = null)
            }

            FavoriteCitiesEvent.RetryFetch -> retryFetchItems()
        }
    }

    private fun fetchItems() = viewModelScope.launch {
        getFavoriteCitiesUseCase.invoke().collectLatest { result ->
            when (result) {
                is DataResult.Success -> _uiState.update {
                    it.copy(
                        favoriteCitiesList = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is DataResult.Failure -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.e?.message.toString()
                    )
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    private fun deleteCity(city: String) =
        viewModelScope.launch {
            deleteFavoriteCityByIdUseCase.invoke(DeleteFavoriteCityByIdUseCase.Params(city))
                .collectLatest { result ->
                    when (result) {
                        is DataResult.Success -> {
                            _uiState.update { it.copy(isLoading = false) }
                            _toastEvent.emit(resourceProvider.getString(R.string.city_removed_from_favorites))
                        }

                        is DataResult.Failure -> {
                            _uiState.update { it.copy(isLoading = false) }
                            _toastEvent.emit(resourceProvider.getString(R.string.error_removing_city_from_favorites))
                        }

                        is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                    }
                }
        }

    private fun retryFetchItems() {
        _uiState.update {
            it.copy(
                errorMessage = "",
                showConfirmDialog = false,
                cityToDelete = null
            )
        }.apply {
            fetchItems()
        }
    }
}
