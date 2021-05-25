package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult

class WeatherViewModel : ViewModel() {
    private val weatherRepo = WeatherRepository()
    private val seoulWeatherList = mutableListOf<WeatherResult>()

    private val _weatherLiveData = MutableLiveData<List<WeatherResult>>()
    val weatherLiveData: LiveData<List<WeatherResult>>
        get() = _weatherLiveData

    fun getSeoulWeather(city: Int) {
        weatherRepo.getWeather(city) {
            seoulWeatherList.addAll(it)
            _weatherLiveData.postValue(seoulWeatherList)
//            Log.d(TAG, "getSeoulWeather: $seoulWeatherList")
        }
    }
}