package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityNameBinding

class CityViewHolder(private val binding: ItemCityNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(city: String) {
        binding.city.text = city
    }
}