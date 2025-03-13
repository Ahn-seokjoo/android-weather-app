package com.example.weatherapp.main.data.local.repository

import com.example.weatherapp.repository.WeatherResult

class RoomRepository : Repository {
    private val _weatherList = mutableListOf<WeatherResult>()

    override suspend fun getAll(): List<WeatherResult> {
        return _weatherList.sortedBy { it.applicable_date }
    }

    override suspend fun addWeather(weather: List<WeatherResult>) {
        _weatherList.addAll(weather)
    }

    override suspend fun updateWeather(weather: WeatherResult) {
        val changeData: List<WeatherResult> = _weatherList.map {

            if (it.id != weather.id) {
                weather
            } else {
                it
            }
        }
        _weatherList.apply {
            clear()
            addAll(changeData)
        }
    }

    override suspend fun deleteAllWeather() {
        _weatherList.clear()
    }
}