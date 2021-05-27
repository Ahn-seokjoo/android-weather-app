package com.example.weatherapp

import java.time.LocalDate

data class WeatherInfo(
    val date: LocalDate,
    val weatherStateAbbr: String,
    val minTemp: Double,
    val maxTemp: Double
)
