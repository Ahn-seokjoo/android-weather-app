package com.example.weatherapp.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.ItemCityNameBinding
import com.example.weatherapp.main.view.holder.CityViewHolder

class CityAdapter(private val cityName: String) : ListAdapter<String,CityViewHolder>(CityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCityNameBinding = ItemCityNameBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(cityName)
    }

    override fun getItemCount(): Int = 1
}