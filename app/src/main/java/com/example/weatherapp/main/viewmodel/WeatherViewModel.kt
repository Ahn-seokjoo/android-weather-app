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
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepo = WeatherRepository()

    private val dbWeatherRepo = LocalRepository(application)
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
        viewModelScope.launch {
            getCityWeatherAsync(SEOUL)
            getCityWeatherAsync(LONDON)
            getCityWeatherAsync(CHICAGO)
            makeWeatherList()
        }.onJoin
    }

    private suspend fun getCityWeatherAsync(city: Int) {
        viewModelScope.launch {
            for (i in 0 until 6) {
                val nextDays = time.plusDays(i.toLong())
                when (city) {
                    SEOUL -> {
                        seoulList.addAll((weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth)))
                    }
                    LONDON -> {
                        londonList.addAll((weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth)))
                    }
                    CHICAGO -> {
                        chicagoList.addAll((weatherRepo.getWeatherAsync(city, nextDays.year, nextDays.monthValue, nextDays.dayOfMonth)))
                    }
                }
            }
        }.join()

    }

    private fun makeWeatherList() {
        val list = listOf(
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("SEOUL"), null),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time,
                    seoulList[0].weather_State_Name,
                    seoulList[0].weather_State_Abbr,
                    seoulList[0].min_Temp,
                    seoulList[0].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(1),
                    seoulList[1].weather_State_Name,
                    seoulList[1].weather_State_Abbr,
                    seoulList[1].min_Temp,
                    seoulList[1].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(2),
                    seoulList[2].weather_State_Name,
                    seoulList[2].weather_State_Abbr,
                    seoulList[2].min_Temp,
                    seoulList[2].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(3),
                    seoulList[3].weather_State_Name,
                    seoulList[3].weather_State_Abbr,
                    seoulList[3].min_Temp,
                    seoulList[3].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(4),
                    seoulList[4].weather_State_Name,
                    seoulList[4].weather_State_Abbr,
                    seoulList[4].min_Temp,
                    seoulList[4].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(5),
                    seoulList[5].weather_State_Name,
                    seoulList[5].weather_State_Abbr,
                    seoulList[5].min_Temp,
                    seoulList[5].max_Temp
                )
            ),
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("LONDON"), null),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time,
                    londonList[0].weather_State_Name,
                    londonList[0].weather_State_Abbr,
                    londonList[0].min_Temp,
                    londonList[0].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(1),
                    londonList[1].weather_State_Name,
                    londonList[1].weather_State_Abbr,
                    londonList[1].min_Temp,
                    londonList[1].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(2),
                    londonList[2].weather_State_Name,
                    londonList[2].weather_State_Abbr,
                    londonList[2].min_Temp,
                    londonList[2].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(3),
                    londonList[3].weather_State_Name,
                    londonList[3].weather_State_Abbr,
                    londonList[3].min_Temp,
                    londonList[3].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(4),
                    londonList[4].weather_State_Name,
                    londonList[4].weather_State_Abbr,
                    londonList[4].min_Temp,
                    londonList[4].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(5),
                    londonList[5].weather_State_Name,
                    londonList[5].weather_State_Abbr,
                    londonList[5].min_Temp,
                    londonList[5].max_Temp
                )
            ),
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("CHICAGO"), null),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time,
                    chicagoList[0].weather_State_Name,
                    chicagoList[0].weather_State_Abbr,
                    chicagoList[0].min_Temp,
                    chicagoList[0].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(1),
                    chicagoList[1].weather_State_Name,
                    chicagoList[1].weather_State_Abbr,
                    chicagoList[1].min_Temp,
                    chicagoList[1].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(2),
                    chicagoList[2].weather_State_Name,
                    chicagoList[2].weather_State_Abbr,
                    chicagoList[2].min_Temp,
                    chicagoList[2].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(3),
                    chicagoList[3].weather_State_Name,
                    chicagoList[3].weather_State_Abbr,
                    chicagoList[3].min_Temp,
                    chicagoList[3].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(4),
                    chicagoList[4].weather_State_Name,
                    chicagoList[4].weather_State_Abbr,
                    chicagoList[4].min_Temp,
                    chicagoList[4].max_Temp
                )
            ),
            WeatherModel(
                WeatherModel.WEATHER_INFO,
                null,
                WeatherInfo(
                    time.plusDays(5),
                    chicagoList[5].weather_State_Name,
                    chicagoList[5].weather_State_Abbr,
                    chicagoList[5].min_Temp,
                    chicagoList[5].max_Temp
                )
            ),
        )
        allWeatherList.clear()
        allWeatherList.addAll(list)
        _weatherLiveData.postValue(allWeatherList)
        Log.d(TAG, "makeWeatherList: $_weatherLiveData")
    }

}