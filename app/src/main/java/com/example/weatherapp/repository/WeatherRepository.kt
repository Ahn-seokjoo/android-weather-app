package com.example.weatherapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val BASE_URL = "https://www.metaweather.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(WeatherAPI::class.java)

    fun getWeather(city: Int, callback: (weathers: List<WeatherResult.WeathersResponse>) -> Unit) {
        val callGetWeathers = api.getWeather(city)

        callGetWeathers.enqueue( // enqueue 로 비동기 실행
            object : retrofit2.Callback<WeatherResult> {
                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    response.body()?.let {
                        Log.d(TAG, "onResponse: ${it.weather_list}")
                        callback.invoke(it.weather_list)
                    }
                }

                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
                }
            }
        )
    }

    suspend fun getWeatherAsync(city: Int) = api.getWeatherAsync(city).weather_list


}