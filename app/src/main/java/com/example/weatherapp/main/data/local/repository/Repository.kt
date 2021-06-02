package com.example.weatherapp.main.data.local.repository

import com.example.weatherapp.repository.WeatherResult

interface Repository {
    fun getAll(): List<WeatherResult>

    fun addWeather(weather: List<WeatherResult>)

    fun updateWeather(weather: List<WeatherResult>)

}