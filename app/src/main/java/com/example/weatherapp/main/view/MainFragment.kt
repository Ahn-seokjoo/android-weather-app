package com.example.weatherapp.main.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.CityInfo
import com.example.weatherapp.WeatherInfo
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.main.WeatherModel
import com.example.weatherapp.main.view.adapter.WeatherAdapter
import com.example.weatherapp.main.viewmodel.WeatherViewModel
import com.example.weatherapp.repository.WeatherResult
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class MainFragment : Fragment() {
    companion object {
        const val SEOUL = 1132599
        const val LONDON = 44418
        const val CHICAGO = 2379574
    }

    private val viewModel: WeatherViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val time = LocalDate.now().atStartOfDay() as LocalDateTime
        val seoulList = mutableListOf<WeatherResult>()

        lifecycleScope.launch {
            seoulList.addAll(viewModel.getCityWeatherAsync(SEOUL))
            Log.d(TAG, "onViewCreated: ${seoulList}")
        }

        val list = listOf(
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("SEOUL"), null),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time, "Heavy cloud", "hc", 2.444, 3.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(1), "Snow", "sn", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(2), "Sleet", "sl", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(3), "Hail", "h", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(4), "Showers", "s", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(5), "clear", "c", 5.444, 10.112)),
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("LONDON"), null),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time, "Heavy cloud", "hc", 2.444, 3.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(1), "Snow", "sn", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(2), "Sleet", "sl", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(3), "Hail", "h", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(4), "Showers", "s", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(5), "clear", "c", 5.444, 10.112)),
            WeatherModel(WeatherModel.CITY_INFO, CityInfo("CHICAGO"), null),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time, "Heavy cloud", "hc", 2.444, 3.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(1), "Snow", "sn", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(2), "Sleet", "sl", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(3), "Hail", "h", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(4), "Showers", "s", 5.444, 10.112)),
            WeatherModel(WeatherModel.WEATHER_INFO, null, WeatherInfo(time.plusDays(5), "clear", "c", 5.444, 10.112)),
        )
        val adapter = WeatherAdapter(list)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)) //구분선 추가

//        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
// coroutine 통해 순차적으로 받기
// 날씨에 따라 받아오기
// room 사용하기
