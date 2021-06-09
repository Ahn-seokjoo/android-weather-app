package com.example.weatherapp.main.data.local.repository

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.main.data.local.WeatherDatabase
import com.example.weatherapp.repository.WeatherResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepository(private val context: Context) : Repository {
    private val db = Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather.db").allowMainThreadQueries().build()

    override fun getAll(): List<WeatherResult> {
        return db.weatherDao().getAll()
    }

    override suspend fun addWeather(weather: List<WeatherResult>) = withContext(Dispatchers.IO) {
        db.weatherDao().addWeather(weather)
    }

    override fun updateWeather(weather: List<WeatherResult>) {
        db.weatherDao().updateWeather(weather)
    }
}