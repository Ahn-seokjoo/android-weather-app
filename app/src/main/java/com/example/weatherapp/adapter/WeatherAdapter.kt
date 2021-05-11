package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.repository.WeatherResult

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weatherList = mutableListOf<WeatherResult.WeathersResponse>()

    fun submitList(data: List<WeatherResult.WeathersResponse>) {
        weatherList.clear()
        weatherList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: WeatherResult.WeathersResponse) {
            binding.applicableDate.text = result.applicable_date.toString()
            binding.weatherStatus.text = result.weather_state_name

            binding.maxTemp.text = result.max_temp.toInt().toString()
            binding.minTemp.text = result.min_temp.toInt().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemBinding =
            RecyclerviewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }


}