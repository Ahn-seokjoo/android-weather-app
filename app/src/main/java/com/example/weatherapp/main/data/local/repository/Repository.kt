package com.example.weatherapp.main.data.local.repository

import com.example.weatherapp.repository.WeatherResult

interface Repository {
    fun getAll(): List<WeatherResult>

    suspend fun addWeather(weather: List<WeatherResult>)

    fun updateWeather(weather: List<WeatherResult>)

}