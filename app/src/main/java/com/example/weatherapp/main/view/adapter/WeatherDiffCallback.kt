package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.main.WeatherModel

object WeatherDiffCallback : DiffUtil.ItemCallback<WeatherModel>() {
    override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem
    }
}
