package com.example.weatherapp.main.data.local.repository

import com.example.weatherapp.repository.WeatherResult

class RoomRepository : Repository {
    private val _seoulWeatherList = mutableListOf<WeatherResult>()

    override fun getAll(): List<WeatherResult> {
        return _seoulWeatherList.sortedBy { it.applicable_date }
    }

    override fun addWeather(weather: List<WeatherResult>) {
        _seoulWeatherList.addAll(weather)
    }

    override fun updateWeather(weather: List<WeatherResult>) {
        _seoulWeatherList.addAll(weather)
    }
}