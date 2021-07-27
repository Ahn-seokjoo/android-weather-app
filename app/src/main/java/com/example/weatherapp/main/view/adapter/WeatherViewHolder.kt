package com.example.weatherapp.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.WeatherModel
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherViewHolder(private val binding: RecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(result: WeatherModel) {
        val today = LocalDate.now().atStartOfDay() as LocalDateTime
        val endDates = result.weatherInfo?.date
        val between = Duration.between(today, endDates).toDays().toInt()

        /** 이미지 Glide로 파싱 **/
        val imageURL = BASE_IMAGE_URL + result.weatherInfo?.weatherStateAbbr + ".png"
        Glide.with(binding.weatherImage.context).load(imageURL)
            .into(binding.weatherImage)

        with(binding) {
            applicableDate.text = when (between) {
                0 -> "Today"
                1 -> "Tomorrow"
                else -> (result.weatherInfo?.date)?.format(DateTimeFormatter.ofPattern("E dd MMM"))
            }
            weatherStatus.text = result.weatherInfo?.weatherStateName
            maxTemp.text = "${result.weatherInfo?.maxTemp?.toInt()}℃"
            minTemp.text = "${result.weatherInfo?.minTemp?.toInt()}℃"
        }
    }
}
