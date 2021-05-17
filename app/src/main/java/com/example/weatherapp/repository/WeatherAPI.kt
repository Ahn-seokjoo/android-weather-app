package com.example.weatherapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {
    @GET("api/location/{city}") //서울 주소
    fun getWeather(
        @Path("city") city: Int
    ): Call<WeatherResult>

    @GET("api/location/{city}")
    suspend fun getWeatherAsync(@Path("city") city: Int): WeatherResult


}