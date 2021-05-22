package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.holder.WeatherViewHolder
import com.example.weatherapp.repository.WeatherResult
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

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
        val today = LocalDateTime.now()
        val endDates = result.applicable_date.toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime()
        val between = Duration.between(today, endDates).toDays().toInt()

        // 이미지 Glide로 파싱
        val imageURL = BASE_IMAGE_URL + getItem(holder.bindingAdapterPosition).weather_State_Abbr + ".png"
        Glide.with(holder.binding.weatherImage.context).load(imageURL)
            .into(holder.binding.weatherImage)

        with(holder.binding) {
            applicableDate.text = when (between) {
                0 -> "Today"
                1 -> "Tommorow"
                else -> SimpleDateFormat("E d MMM").format(result.applicable_date)
            }
            weatherStatus.text = result.weather_State_Name
            maxTemp.text = "${result.max_Temp.toInt()}℃"
            minTemp.text = "${result.min_Temp.toInt()}℃"
        }
    }

}