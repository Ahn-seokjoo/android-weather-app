package com.example.weatherapp

import java.time.LocalDateTime

data class WeatherInfo(
    val date: LocalDateTime,
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val minTemp: Double,
    val maxTemp: Double
)
