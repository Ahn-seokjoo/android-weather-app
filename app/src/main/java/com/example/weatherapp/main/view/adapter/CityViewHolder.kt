package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityNameBinding
import com.example.weatherapp.main.WeatherModel

class CityViewHolder(private val binding: ItemCityNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(city: WeatherModel) {
        binding.city.text = city.cityInfo?.city
    }
}
