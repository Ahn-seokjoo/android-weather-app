package com.example.weatherapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.repository.WeatherResult

object WeatherDiffCallback : DiffUtil.ItemCallback<WeatherResult.WeathersResponse>() {

    override fun areItemsTheSame(
        oldItem: WeatherResult.WeathersResponse,
        newItem: WeatherResult.WeathersResponse
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherResult.WeathersResponse,
        newItem: WeatherResult.WeathersResponse
    ): Boolean {
        return oldItem == newItem
    }
}