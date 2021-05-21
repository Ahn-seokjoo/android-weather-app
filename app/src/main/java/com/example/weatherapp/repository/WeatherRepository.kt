package com.example.weatherapp.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val BASE_URL = "https://www.metaweather.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(WeatherAPI::class.java)

    suspend fun getWeatherAsync(city: Int) = api.getWeatherAsync(city).weather_List
}