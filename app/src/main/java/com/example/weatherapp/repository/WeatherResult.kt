package com.example.weatherapp.repository

import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherResult(
    @SerializedName("consolidated_weather") val weather_List: List<WeathersResponse>,
){
    data class WeathersResponse(
        @SerializedName("applicable_date") val applicable_date: Date,
        @SerializedName("weather_state_name") val weather_State_Name: String,
        @SerializedName("weather_state_abbr") val weather_State_Abbr: String,
        @SerializedName("min_temp") val min_Temp: Double,
        @SerializedName("max_temp") val max_Temp: Double
    )
}

