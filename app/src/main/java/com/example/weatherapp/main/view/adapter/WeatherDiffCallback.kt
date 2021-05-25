package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.repository.WeatherResult

object WeatherDiffCallback : DiffUtil.ItemCallback<WeatherResult>() {

    override fun areItemsTheSame(
        oldItem: WeatherResult,
        newItem: WeatherResult
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherResult,
        newItem: WeatherResult
    ): Boolean {
        return oldItem == newItem
    }
}