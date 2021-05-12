package com.example.weatherapp.repository

import retrofit2.Call
import retrofit2.http.GET

interface WeatherAPI {
    @GET("api/location/1132599") //서울 주소
    fun getWeather(

    ): Call<WeatherResult>
}