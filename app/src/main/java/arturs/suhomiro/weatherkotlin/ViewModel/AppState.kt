package arturs.suhomiro.weatherkotlin.ViewModel

import arturs.suhomiro.weatherkotlin.Model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
