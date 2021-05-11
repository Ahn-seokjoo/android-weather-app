package com.example.weatherapp.repository

import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherResult(
    @SerializedName("consolidated_weather") var weather_list: List<WeathersResponse>,
    @SerializedName("title") var town: String
){

data class WeathersResponse(
    @SerializedName("applicable_date") var applicable_date: Date,
    @SerializedName("weather_state_name") var weather_state_name: String,
    @SerializedName("weater_state_abbr") var weather_state_abbr: String,
    @SerializedName("min_temp") var min_temp: Double,
    @SerializedName("max_temp") var max_temp: Double
)}

