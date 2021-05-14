package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.holder.WeatherViewHolder
import com.example.weatherapp.repository.WeatherResult
import java.text.SimpleDateFormat

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"
private var count = 1

class WeatherAdapter :
    ListAdapter<WeatherResult.WeathersResponse, WeatherViewHolder>(WeatherDiffCallback) {

    //    private val weatherList = mutableListOf<WeatherResult.WeathersResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemBinding =
            RecyclerviewItemBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val result = getItem(position)
        // 날짜 계산
        if (count == 1) {
            holder.binding.applicableDate.text = "Today"
            count = 2
        } else if (count == 2) {
            holder.binding.applicableDate.text = "Tommorrow"
            count += 1
        } else {
            holder.binding.applicableDate.text = SimpleDateFormat("E d MMM").format(result.applicable_date)
            if (count == 5) {
                count = 1
            }
        }

        // 이미지 Glide로 파싱
        val imageURL = BASE_IMAGE_URL + getItem(holder.adapterPosition).weather_state_abbr + ".png"
        Glide.with(holder.binding.weatherImage.context).load(imageURL)
            .into(holder.binding.weatherImage)

        with(holder.binding) {
            weatherStatus.text = result.weather_state_name
            maxTemp.text = result.max_temp.toInt().toString()
            minTemp.text = result.min_temp.toInt().toString()
        }
    }

}