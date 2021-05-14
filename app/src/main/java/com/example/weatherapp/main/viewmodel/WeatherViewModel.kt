package com.example.weatherapp.main.viewmodel

import com.example.weatherapp.data.WeatherData

class WeatherViewModel {
    private val _weatherList = mutableListOf<WeatherData>()

    fun updateWeatherList(weather: WeatherData) {
        _weatherList.add(weather)
    }
}