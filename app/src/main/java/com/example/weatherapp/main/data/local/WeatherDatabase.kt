package com.example.weatherapp.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.main.ext.RoomTypeConverter
import com.example.weatherapp.repository.WeatherResult

@Database(entities = [WeatherResult::class], version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}