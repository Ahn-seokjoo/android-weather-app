package com.example.weatherapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val BASE_URL = "https://www.metaweather.com/"

    private val gson = GsonBuilder().setDateFormat("").create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val api = retrofit.create(WeatherAPI::class.java)

    fun getWeather(){
        val callGetWeathers = api.getWeather()

        callGetWeathers.enqueue( // enqueue 로 비동기 실행
            object : retrofit2.Callback<WeatherResult> {
                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    response.body()?.let {
                        Log.d(TAG, "onResponse: ${it.weather_list}")
                    }
                }

                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    Log.d(TAG, "onFailure: "+t.message)
                }
            }
        )
    }


}