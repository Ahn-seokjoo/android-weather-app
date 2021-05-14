package com.example.weatherapp.main.viewmodel

import com.example.weatherapp.repository.WeatherResult

class WeatherViewModel {
    private val _weatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()

    fun updateWeatherList(weather: List<WeatherResult.WeathersResponse>) {
        _weatherList.add(weather)
    }
}