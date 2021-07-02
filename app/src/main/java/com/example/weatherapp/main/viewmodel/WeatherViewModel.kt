package com.example.weatherapp.main.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
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
import com.example.weatherapp.repository.WeatherResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepo = WeatherRepository()

    val dbWeatherRepo = LocalRepository(application)
    private val roomRepo = RoomRepository()

    private val time = LocalDate.now().atStartOfDay() as LocalDateTime

    private val seoulList = mutableListOf<WeatherResult>()
    private val londonList = mutableListOf<WeatherResult>()
    private val chicagoList = mutableListOf<WeatherResult>()

    private val allWeatherList = mutableListOf<WeatherModel>()

    private val _weatherLiveData = MutableLiveData<List<WeatherModel>>()
    val weatherLiveData: LiveData<List<WeatherModel>>
        get() = _weatherLiveData

    suspend fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            getCityWeatherAsync(SEOUL)
            getCityWeatherAsync(LONDON)
            getCityWeatherAsync(CHICAGO)
        }.onJoin
    }

    suspend fun setWeather() {
        setWeatherList()
    }

    suspend fun updateWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateWeather: date:${Date.from(time.atZone(ZoneId.systemDefault()).toInstant())}")
            Log.d(TAG, "updateWeather: dbdate:${dbWeatherRepo.getAll()}")
            if (Date.from(time.atZone(ZoneId.systemDefault()).toInstant()) != dbWeatherRepo.getAll()[0].applicable_date) {
                dbWeatherRepo.deleteAllWeather()
                getWeather()
            }
        }
    }

    private suspend fun getCityWeatherAsync(city: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0 until 6) {
                val nextDays = time.plusDays(i.toLong())
                when (city) {
                    SEOUL -> {
                        dbWeatherRepo.addWeather(weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth))
                    }
                    LONDON -> {
                        dbWeatherRepo.addWeather(weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth))
                    }
                    CHICAGO -> {
                        dbWeatherRepo.addWeather(weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth))
                    }
                }
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
                    dbWeatherRepo.getAll()[0].weather_State_Name,
                    dbWeatherRepo.getAll()[0].weather_State_Abbr,
                    dbWeatherRepo.getAll()[0].min_Temp,
                    dbWeatherRepo.getAll()[0].max_Temp
                )
            )
        )
        allWeatherList.clear()
        allWeatherList.addAll(list)
        _weatherLiveData.postValue(allWeatherList)
    }

}