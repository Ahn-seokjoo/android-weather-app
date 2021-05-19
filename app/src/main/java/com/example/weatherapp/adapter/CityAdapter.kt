package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityNameBinding

class CityAdapter(private val cityName: String) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(val binding: ItemCityNameBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCityNameBinding =
            ItemCityNameBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.city.text = cityName
    }

    override fun getItemCount(): Int = 1
}