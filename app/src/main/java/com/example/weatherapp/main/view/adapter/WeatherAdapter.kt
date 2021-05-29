package com.example.weatherapp.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemCityNameBinding
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.main.WeatherModel
import com.example.weatherapp.main.WeatherModel.Companion.CITY_INFO
import com.example.weatherapp.main.WeatherModel.Companion.WEATHER_INFO
import com.example.weatherapp.repository.WeatherResult
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"

class WeatherAdapter(private val list: List<WeatherModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val weatherList = mutableListOf<WeatherResult>()

    fun submitList(data: List<WeatherResult>) {
        weatherList.clear()
        weatherList.addAll(data)
        notifyDataSetChanged()
        weatherList.sortBy { it.applicable_date }
    }

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
        return list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when ((list[position].type)) {
            CITY_INFO -> (holder as CityViewHolder).onBind("SEOUL")
            WEATHER_INFO -> {
                (holder as WeatherViewHolder).onBind(list[position])
            }
        }
    }

}

class CityViewHolder(private val binding: ItemCityNameBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(city: String) {
        binding.city.text = city
    }
}

class WeatherViewHolder(private val binding: RecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(result: WeatherModel) {

        val today = LocalDate.now().atStartOfDay()
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