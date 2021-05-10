package com.example.weatherapp.holder

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class WeatherViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val textView: TextView
    init {
        textView = view.findViewById(R.id.day)
    }
}