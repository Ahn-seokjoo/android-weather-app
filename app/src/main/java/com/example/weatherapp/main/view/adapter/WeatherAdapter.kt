package com.example.weatherapp.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityNameBinding
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.WeatherModel
import com.example.weatherapp.main.WeatherModel.Companion.CITY_INFO
import com.example.weatherapp.main.WeatherModel.Companion.WEATHER_INFO
import com.example.weatherapp.repository.WeatherResult

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"

class WeatherAdapter :
    ListAdapter<List<WeatherResult>, RecyclerView.ViewHolder>(WeatherDiffCallback) {
    val weatherList = mutableListOf<WeatherModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            CITY_INFO -> {
                val binding: ItemCityNameBinding = ItemCityNameBinding.inflate(inflater, parent, false)
                CityViewHolder(binding)
            }
            WEATHER_INFO -> {
                val binding: RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(inflater, parent, false)
                WeatherViewHolder(binding)
            }
            else -> throw RuntimeException("알수 없는 뷰타입 에러")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return weatherList[position].type
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when ((weatherList[position].type)) {
            CITY_INFO -> (holder as CityViewHolder).onBind(weatherList[position].cityInfo!!.city)
            WEATHER_INFO -> {
                (holder as WeatherViewHolder).onBind(weatherList[position])
            }
        }
    }

    companion object {
        fun submitList(weatherAdapter: WeatherAdapter, data: List<WeatherModel>) {
            weatherAdapter.weatherList.clear()
            weatherAdapter.weatherList.addAll(data)
            weatherAdapter.weatherList.sortBy { it.weatherInfo?.date }
            weatherAdapter.notifyDataSetChanged()
        }
    }

}
