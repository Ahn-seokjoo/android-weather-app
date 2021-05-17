package com.example.weatherapp.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// 1. SEOUL,LONDON, CHICAGO 쓰기
// 2. 3번 불러야하는 것 (도시별)
// 3. 도씨 넣기
