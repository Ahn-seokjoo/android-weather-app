package com.example.weatherapp.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.main.data.local.repository.LocalRepository
import com.example.weatherapp.main.data.local.repository.RoomRepository
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepo = WeatherRepository()

    private val dbWeatherRepo = LocalRepository(application)
    private val roomRepo = RoomRepository()

    private val time = LocalDate.now().atStartOfDay() as LocalDateTime

    private val _weatherLiveData = MutableLiveData<List<WeatherResult>>()
    val weatherLiveData: LiveData<List<WeatherResult>>
        get() = _weatherLiveData

    suspend fun getCityWeatherAsync(city: Int): List<WeatherResult> {
        for (i in 0 until 6) {
            val nextDays = time.plusDays(i.toLong())
            dbWeatherRepo.addWeather((weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth)))
            _weatherLiveData.postValue(roomRepo.getAll())
        }
        return dbWeatherRepo.getAll()
    }
}