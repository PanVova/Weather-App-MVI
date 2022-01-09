package com.example.weatherapi.domain.viewState

sealed class State {
    object LoadingState : State()
    data class DataState(val data: Any) : State()
    data class ErrorState(val error: String) : State()
    data class EmptyState(val empty: String) : State()
}