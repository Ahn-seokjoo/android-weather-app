package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherResult

class WeatherViewModel : ViewModel() {
    private val _weatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()

    val weatherList: List<List<WeatherResult.WeathersResponse>>
        get() = _weatherList
    val weatherListLiveData: LiveData<List<List<WeatherResult.WeathersResponse>>>
        get() = MutableLiveData(_weatherList)

    fun updateWeatherList(weather: List<WeatherResult.WeathersResponse>) {
        _weatherList.add(weather)
    }
}