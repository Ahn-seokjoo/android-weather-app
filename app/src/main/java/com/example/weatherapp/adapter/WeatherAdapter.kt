package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.RecyclerviewItemBinding
import com.example.weatherapp.repository.WeatherResult
import java.text.SimpleDateFormat

const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weatherList = mutableListOf<WeatherResult.WeathersResponse>()

    fun submitList(data: List<WeatherResult.WeathersResponse>) {
        weatherList.clear()
        weatherList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: WeatherResult.WeathersResponse) {
            val dateFormat = SimpleDateFormat("EE dd MMMM").format(result.applicable_date)
            // 날짜 형식 변환하여 저장
            binding.applicableDate.text = dateFormat
            // 이미지 Glide로 파싱
            val imageURL = BASE_IMAGE_URL + result.weather_state_abbr + ".png"
            Glide.with(binding.weatherImage.context).load(imageURL).into(binding.weatherImage)

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