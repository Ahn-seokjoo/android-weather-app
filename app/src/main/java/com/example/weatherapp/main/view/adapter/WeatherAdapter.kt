package com.example.weatherapp.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.view.holder.WeatherViewHolder
import com.example.weatherapp.repository.WeatherResult

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"

class WeatherAdapter() :
    ListAdapter<WeatherResult, WeatherViewHolder>(WeatherDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}