package com.example.weatherapp.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class WeatherResult(
        @SerializedName("applicable_date") val applicable_date: LocalDateTime,
        @SerializedName("weather_state_name") val weather_State_Name: String,
        @SerializedName("weather_state_abbr") val weather_State_Abbr: String,
        @SerializedName("min_temp") val min_Temp: Double,
        @SerializedName("max_temp") val max_Temp: Double
) : Parcelable


