package com.example.weatherapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class WeatherRepository {
    private val BASE_URL = "https://www.metaweather.com/"
    private val time: LocalDate = LocalDate.now()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(WeatherAPI::class.java)
    fun getWeather(city: Int, callback: (weathers: List<WeatherResult>) -> Unit) {
        for (i in 0 until 6) {
            val nextDay = time.plusDays(i.toLong())
            val callGetWeathers = api.getWeather(city, nextDay.year, nextDay.monthValue, nextDay.dayOfMonth)

            callGetWeathers.enqueue( // enqueue 로 비동기 실행
                object : retrofit2.Callback<List<WeatherResult>> {
                    override fun onResponse(
                        call: Call<List<WeatherResult>>,
                        response: Response<List<WeatherResult>>
                    ) {
                        response.body()?.let {
                            callback.invoke(it.slice(0..0))
                        }
                    }

                    override fun onFailure(call: Call<List<WeatherResult>>, t: Throwable) {
                        Log.d(TAG, "onFailure: " + t.message)
                    }


                }
            )
        }
    }

}