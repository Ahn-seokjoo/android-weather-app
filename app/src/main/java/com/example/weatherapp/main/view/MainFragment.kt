package com.example.weatherapp.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.CityInfo
import com.example.weatherapp.WeatherInfo
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.main.WeatherModel
import com.example.weatherapp.main.view.adapter.WeatherAdapter
import com.example.weatherapp.main.viewmodel.WeatherViewModel
import java.time.LocalDateTime

class MainFragment() : Fragment() {
    companion object {
        const val SEOUL = 1132599
        const val LONDON = 44418
        const val CHICAGO = 2379574
    }

    private val viewModel: WeatherViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("SEOUL"), null),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now(), "Heavy cloud", "hc", 2.444, 3.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now().plusDays(1), "Snow", "sn", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now().plusDays(2), "Sleet", "sl", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now().plusDays(3), "Hail", "h", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now().plusDays(4), "Showers", "s", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(LocalDateTime.now().plusDays(5), "clear", "c", 5.444, 10.112)),
        )
        val adapter = WeatherAdapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)) //구분선 추가

        viewModel.getSeoulWeather(SEOUL)
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}