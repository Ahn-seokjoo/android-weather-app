package com.example.weatherapp.main.data.local.repository

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.main.data.local.WeatherDatabase
import com.example.weatherapp.repository.WeatherResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepository(private val context: Context) : Repository {
    private val db = Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather.db")
        .build()

    override suspend fun getAll(): List<WeatherResult> = withContext(Dispatchers.IO) {
        db.weatherDao().getAll()
    }

    override suspend fun addWeather(weather: List<WeatherResult>) = withContext(Dispatchers.IO) {
        db.weatherDao().addWeather(weather)
    }

    override suspend fun updateWeather(weather: WeatherResult) {
        db.weatherDao().updateWeather(weather)
    }

    override suspend fun deleteAllWeather() {
        db.weatherDao().deleteAllWeather(db.weatherDao().getAll())
    }
}