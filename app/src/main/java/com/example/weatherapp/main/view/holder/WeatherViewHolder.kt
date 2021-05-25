package com.example.weatherapp.main.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.view.adapter.BASE_IMAGE_URL
import com.example.weatherapp.repository.WeatherResult
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

class WeatherViewHolder(private val binding: RecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(result: WeatherResult) {
        val today = LocalDateTime.now()
        val endDates = result.applicable_date.toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime()
        val between = Duration.between(today, endDates).toDays().toInt()

        /** 이미지 Glide로 파싱 **/
        val imageURL = BASE_IMAGE_URL + result.weather_State_Abbr + ".png"
        Glide.with(binding.weatherImage.context).load(imageURL)
            .into(binding.weatherImage)

        with(binding) {
            applicableDate.text = when (between) {
                0 -> "Today"
                1 -> "Tomorrow"
                else -> SimpleDateFormat("E d MMM").format(result.applicable_date)
            }
            weatherStatus.text = result.weather_State_Name
            maxTemp.text = "${result.max_Temp.toInt()}℃"
            minTemp.text = "${result.min_Temp.toInt()}℃"
        }
    }
}

