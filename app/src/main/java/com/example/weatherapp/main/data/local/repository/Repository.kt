package com.example.weatherapp.main.data.local.repository

import com.example.weatherapp.repository.WeatherResult

interface Repository {
    suspend fun getAll(): List<WeatherResult>

    suspend fun addWeather(weather: List<WeatherResult>)

    suspend fun updateWeather(weather: WeatherResult)

    suspend fun deleteAllWeather()
}