package com.example.weatherapp.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.main.viewmodel.WeatherViewModel
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherResult

class MainFragment(val viewModel: WeatherViewModel) : Fragment() {
    companion object {
        const val SEOUL = 1132599
        const val LONDON = 44418
        const val CHICAGO = 2379574
    }

    private val weatherRepo = WeatherRepository()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val seoulWeatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()
        val londonWeatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()
        val chicagoWeatherList = mutableListOf<List<WeatherResult.WeathersResponse>>()

        val adapter = WeatherAdapter()
        binding.recyclerView.adapter = adapter

        weatherRepo.getWeather(SEOUL) {
            viewModel.updateWeatherList(it)
            adapter.submitList(it)
        }
        weatherRepo.getWeather(LONDON) {
            viewModel.updateWeatherList(it)
            adapter.submitList(it)
        }
        weatherRepo.getWeather(CHICAGO) {
            viewModel.updateWeatherList(it)
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}