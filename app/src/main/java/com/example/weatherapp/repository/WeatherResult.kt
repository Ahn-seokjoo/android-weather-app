package com.example.weatherapp.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class WeatherResult(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") val id: Long,
    @SerializedName("applicable_date") val applicable_date: Date,
    @SerializedName("weather_state_name") val weather_State_Name: String,
    @SerializedName("weather_state_abbr") val weather_State_Abbr: String,
    @SerializedName("min_temp") val min_Temp: Double,
    @SerializedName("max_temp") val max_Temp: Double
)


