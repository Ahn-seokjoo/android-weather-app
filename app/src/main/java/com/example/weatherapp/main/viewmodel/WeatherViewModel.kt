package com.example.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherViewModel : ViewModel() {
    private val weatherRepo = WeatherRepository()
    private val time = LocalDate.now().atStartOfDay() as LocalDateTime

    private val _seoulWeatherList = mutableListOf<WeatherResult>()
    val seoulWeatherList: List<WeatherResult>
        get() = _seoulWeatherList.sortedBy { it.applicable_date }

    private val _weatherLiveData = MutableLiveData<List<WeatherResult>>()
    val weatherLiveData: LiveData<List<WeatherResult>>
        get() = _weatherLiveData

    suspend fun getCityWeatherAsync(city: Int): List<WeatherResult> {
//        weatherRepo.getWeatherAsync(city,year,month, day) {
//            _seoulWeatherList.addAll(it)
//            _weatherLiveData.postValue(_seoulWeatherList)
//        }
        coroutineScope {
            async {
                for (i in 0 until 6) {
                    val nextDays = time.plusDays(i.toLong())
                    _seoulWeatherList.addAll(weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth))
                }
            }.await()
        }
        return _seoulWeatherList
    }
}