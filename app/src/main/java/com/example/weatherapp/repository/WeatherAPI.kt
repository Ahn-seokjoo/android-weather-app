package com.example.weatherapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {
    @GET("api/location/{city}/{year}/{month}/{day}") //서울 주소
    fun getWeather(
        @Path("city") city: Int,
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): Call<List<WeatherResult>>

    @GET("api/location/{city}/{year}/{month}/{day}")
    suspend fun getWeatherAsync(
        @Path("city") city: Int,
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): List<WeatherResult>
}