package com.example.weatherapi.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapi.domain.useCase.GetWeatherCitiesUseCase
import com.example.weatherapi.domain.viewState.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(
    private val getWeatherCitiesUseCase: GetWeatherCitiesUseCase,
) : ViewModel() {

    var cities = MutableLiveData<State>()
        private set

    fun getCities(query: String) {
        viewModelScope.launch {
            cities.value = State.LoadingState

            getWeatherCitiesUseCase.loadCities(query)
                .catch {
                    cities.value = State.ErrorState(it.toString())
                }
                .collect {
                    if (it.isEmpty()) {
                        cities.value = State.EmptyState
                    } else {
                        cities.value = State.DataState(it)
                    }
                }
        }
    }
}


