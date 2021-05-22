package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    val weatherRepo = WeatherRepository()
}