package com.example.weatherapp.data

data class WeatherData(
    var day: String,
    var weatherImage: String,
    var weatherStatus: String,
    var weatherabbr: String,
    var max_temp: Int,
    var min_temp: Int
)
