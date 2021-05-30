package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult

class WeatherViewModel : ViewModel() {
    private val weatherRepo = WeatherRepository()

    private val _seoulWeatherList = mutableListOf<WeatherResult>()
    val seoulWeatherList: List<WeatherResult>
        get() = _seoulWeatherList

    private val _weatherLiveData = MutableLiveData<List<WeatherResult>>()
    val weatherLiveData: LiveData<List<WeatherResult>>
        get() = _weatherLiveData

    fun getCityWeather(city: Int) {
        weatherRepo.getWeather(city) {
            _seoulWeatherList.addAll(it)
            _weatherLiveData.postValue(_seoulWeatherList)
        }
    }
}