package com.example.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.repository.WeatherRepository

private const val TAG = "MyActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherRepo = WeatherRepository()

        val adapter = WeatherAdapter()
        weatherRepo.getWeather {
            adapter.submitList(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }
}
// 1. SEOUL,LONDON, CHICAGO 쓰기
// 2. 위에 날짜 Today, tommorrow
// 3. 3번 불러야하는 것 (도시별)
// 4. 리사이클러뷰 diff로 바꾸기
// 5. 도씨 넣기
