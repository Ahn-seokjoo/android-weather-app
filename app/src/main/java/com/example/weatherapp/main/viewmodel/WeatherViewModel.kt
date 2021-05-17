package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.MemoryWeatherRepository
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult

class WeatherViewModel : ViewModel() {
    private val repository = MemoryWeatherRepository()
    val weatherRepo = WeatherRepository()


    private val _weatherListLiveData = MutableLiveData<List<List<WeatherResult.WeathersResponse>>>()

    val weatherListLiveData: LiveData<List<List<WeatherResult.WeathersResponse>>>
        get() = _weatherListLiveData

    fun updateWeatherList(weather: List<WeatherResult.WeathersResponse>) {
        repository.updateWeatherList(weather)
        _weatherListLiveData.value = repository.weatherList
    }
}