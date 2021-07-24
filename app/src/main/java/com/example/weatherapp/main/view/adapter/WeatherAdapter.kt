package com.example.weatherapp.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityNameBinding
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.WeatherModel

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"

class WeatherAdapter(private val itemClickListener: (weatherList: WeatherModel) -> Unit) :
    ListAdapter<WeatherModel, RecyclerView.ViewHolder>(WeatherDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            WeatherModel.CITY_INFO -> {
                val binding: ItemCityNameBinding = ItemCityNameBinding.inflate(inflater, parent, false)
                CityViewHolder(binding)
            }
            WeatherModel.WEATHER_INFO -> {
                val binding: RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(inflater, parent, false)
                WeatherViewHolder(binding)
            }
            else -> throw RuntimeException("알 수 없는 뷰타입 에러")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            WeatherModel.CITY_INFO -> 0
            WeatherModel.WEATHER_INFO -> 1
            else -> throw java.lang.RuntimeException("알 수 없는 뷰타입 에러")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).type) {
            WeatherModel.CITY_INFO -> {
                (holder as CityViewHolder).onBind(getItem(position))
            }
            WeatherModel.WEATHER_INFO -> {
                (holder as WeatherViewHolder).onBind(getItem(position))
            }
        }
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(getItem(position) as WeatherModel)
        }
    }
}
