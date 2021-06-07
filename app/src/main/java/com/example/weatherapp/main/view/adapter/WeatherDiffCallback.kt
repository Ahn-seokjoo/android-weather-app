package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.repository.WeatherResult

object WeatherDiffCallback : DiffUtil.ItemCallback<List<WeatherResult>>() {
    override fun areItemsTheSame(oldItem: List<WeatherResult>, newItem: List<WeatherResult>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: List<WeatherResult>, newItem: List<WeatherResult>): Boolean {
        return oldItem == newItem
    }
}