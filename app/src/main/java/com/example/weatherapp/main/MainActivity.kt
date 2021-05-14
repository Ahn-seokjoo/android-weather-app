package com.example.weatherapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.main.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = WeatherViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MyFragmentFactory(viewModel)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// 1. SEOUL,LONDON, CHICAGO 쓰기
// 2. 3번 불러야하는 것 (도시별)
// 3. 도씨 넣기
