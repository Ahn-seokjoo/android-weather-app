package com.example.weatherapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("api/location/2379574") //시카고 주소
    fun getWeather(

    ): Call<WeatherResult>
}