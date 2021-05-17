package com.example.weatherapp.repository

class MemoryWeatherRepository {
    private val _weatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()

    val weatherList: List<List<WeatherResult.WeathersResponse>>
        get() = _weatherList

    fun updateWeatherList(weather: List<WeatherResult.WeathersResponse>) {
        _weatherList.add(weather)
    }
}