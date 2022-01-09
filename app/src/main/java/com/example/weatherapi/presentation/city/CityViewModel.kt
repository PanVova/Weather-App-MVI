package com.example.weatherapi.presentation.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapi.domain.useCase.GetWeatherCityUseCase
import com.example.weatherapi.domain.viewState.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityViewModel @Inject constructor(
    private val getWeatherCityUseCase: GetWeatherCityUseCase
) : ViewModel() {

    var weatherCity = MutableLiveData<State>()
        private set

    fun getCity(city: Int) {
        viewModelScope.launch {
            weatherCity.value = State.LoadingState

            getWeatherCityUseCase.loadCity(city)
                .catch {
                    weatherCity.value = State.ErrorState(it.toString())
                }.collect { weather ->
                    weatherCity.value = State.DataState(weather)
                }
        }

    }
}


