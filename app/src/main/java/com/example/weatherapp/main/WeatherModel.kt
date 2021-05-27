package com.example.weatherapp.main

import com.example.weatherapp.CityInfo
import com.example.weatherapp.WeatherInfo

data class WeatherModel(
    val type: Int,
    val cityInfo: CityInfo?,
    val weatherInfo: WeatherInfo?
) {
    companion object {
        const val CITY_INFO = 0
        const val WEATHER_INFO = 1
    }

}