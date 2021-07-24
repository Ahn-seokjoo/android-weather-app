package com.example.weatherapp.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.CityInfo
import com.example.weatherapp.WeatherInfo
import com.example.weatherapp.main.WeatherModel
import com.example.weatherapp.main.data.local.repository.LocalRepository
import com.example.weatherapp.main.data.local.repository.RoomRepository
import com.example.weatherapp.main.view.MainFragment.Companion.CHICAGO
import com.example.weatherapp.main.view.MainFragment.Companion.LONDON
import com.example.weatherapp.main.view.MainFragment.Companion.SEOUL
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepo = WeatherRepository()

    private val dbWeatherRepo = LocalRepository(application)
    private val roomRepo = RoomRepository()

    private val time = LocalDate.now().atStartOfDay() as LocalDateTime

    private val _weatherLiveData = MutableLiveData<List<WeatherModel>>()
    val weatherLiveData: LiveData<List<WeatherModel>>
        get() = _weatherLiveData

    suspend fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            getCityWeatherAsync(SEOUL)
            getCityWeatherAsync(LONDON)
            getCityWeatherAsync(CHICAGO)
        }.join()
    }

    suspend fun setWeather() {
        setWeatherList()
    }

    private suspend fun getCityWeatherAsync(city: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0 until 6) {
                val nextDays = time.plusDays(i.toLong())
                roomRepo.addWeather(weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth))
            }
        }.join()

    }

    private suspend fun setWeatherList() {
        val list = listOf(
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("SEOUL"), null),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time,
                    roomRepo.getAll()[0].weather_State_Name,
                    roomRepo.getAll()[0].weather_State_Abbr,
                    roomRepo.getAll()[0].min_Temp,
                    roomRepo.getAll()[0].max_Temp
                )
            )
        )
        _weatherLiveData.postValue(list)
    }

}
