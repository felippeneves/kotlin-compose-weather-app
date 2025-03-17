package com.felippeneves.kotlin_compose_weather_app.presentation.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.DataResult
import com.felippeneves.kotlin_compose_weather_app.core.ResourceProvider
import com.felippeneves.kotlin_compose_weather_app.domain.usecase.SaveCurrentCityUseCase
import com.felippeneves.kotlin_compose_weather_app.presentation.features.search.events.SearchCityEvent
import com.felippeneves.kotlin_compose_weather_app.presentation.features.search.state.SearchCityState
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
class SearchCityViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val saveCurrentCityUseCase: SaveCurrentCityUseCase
) : ViewModel() {

    // region states

    private val _uiState = MutableStateFlow(SearchCityState())
    val uiState: StateFlow<SearchCityState> = _uiState.asStateFlow()

    private val _finishScreen = MutableSharedFlow<Unit>()
    val finishScreen: SharedFlow<Unit> = _finishScreen

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    // endregion states

    fun onEvent(event: SearchCityEvent) {
        when (event) {
            is SearchCityEvent.UpdateQuery -> _uiState.update { it.copy(query = event.query) }
            SearchCityEvent.ConfirmCity -> confirmCity()
        }
    }

    private fun confirmCity() = viewModelScope.launch {
        if (_uiState.value.query.isNotBlank()) {
            addCurrentCity()
        } else {
            _toastEvent.emit(resourceProvider.getString(R.string.please_enter_city_name))
        }
    }

    private suspend fun addCurrentCity() {
        saveCurrentCityUseCase.invoke(
            SaveCurrentCityUseCase.Params(_uiState.value.query.trim())
        ).collectLatest { result ->
            when (result) {
                is DataResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _finishScreen.emit(Unit)
                }

                is DataResult.Failure -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _toastEvent.emit(resourceProvider.getString(R.string.error_unexpected_error_occurred))
                }

                is DataResult.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }
    }
}
