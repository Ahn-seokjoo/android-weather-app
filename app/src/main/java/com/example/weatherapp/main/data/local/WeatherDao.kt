package com.example.weatherapp.main.data.local

import androidx.room.*
import com.example.weatherapp.repository.WeatherResult

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherResult")
    fun getAll(): List<WeatherResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeather(weather: List<WeatherResult>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWeather(weather: List<WeatherResult>)
}