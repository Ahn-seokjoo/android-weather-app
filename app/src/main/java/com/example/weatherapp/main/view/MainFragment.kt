package com.example.weatherapp.main.view

import android.os.Bundle
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
        val londonList = mutableListOf<WeatherResult>()
        val chicagoList = mutableListOf<WeatherResult>()

        lifecycleScope.launch {
            seoulList.addAll(viewModel.getCityWeatherAsync(SEOUL))
            londonList.addAll(viewModel.getCityWeatherAsync(LONDON))
            chicagoList.addAll(viewModel.getCityWeatherAsync(CHICAGO))

            val list = listOf(
                WeatherModel(WeatherModel.CITY_INFO, CityInfo("SEOUL"), null),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(time, seoulList[0].weather_State_Name, seoulList[0].weather_State_Abbr, seoulList[0].min_Temp, seoulList[0].max_Temp)
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(1),
                        seoulList[1].weather_State_Name,
                        seoulList[1].weather_State_Abbr,
                        seoulList[1].min_Temp,
                        seoulList[1].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(2),
                        seoulList[2].weather_State_Name,
                        seoulList[2].weather_State_Abbr,
                        seoulList[2].min_Temp,
                        seoulList[2].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(3),
                        seoulList[3].weather_State_Name,
                        seoulList[3].weather_State_Abbr,
                        seoulList[3].min_Temp,
                        seoulList[3].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(4),
                        seoulList[4].weather_State_Name,
                        seoulList[4].weather_State_Abbr,
                        seoulList[4].min_Temp,
                        seoulList[4].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(5),
                        seoulList[5].weather_State_Name,
                        seoulList[5].weather_State_Abbr,
                        seoulList[5].min_Temp,
                        seoulList[5].max_Temp
                    )
                ),
                WeatherModel(WeatherModel.CITY_INFO, CityInfo("LONDON"), null),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time,
                        londonList[0].weather_State_Name,
                        londonList[0].weather_State_Abbr,
                        londonList[0].min_Temp,
                        londonList[0].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(1),
                        londonList[1].weather_State_Name,
                        londonList[1].weather_State_Abbr,
                        londonList[1].min_Temp,
                        londonList[1].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(2),
                        londonList[2].weather_State_Name,
                        londonList[2].weather_State_Abbr,
                        londonList[2].min_Temp,
                        londonList[2].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(3),
                        londonList[3].weather_State_Name,
                        londonList[3].weather_State_Abbr,
                        londonList[3].min_Temp,
                        londonList[3].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(4),
                        londonList[4].weather_State_Name,
                        londonList[4].weather_State_Abbr,
                        londonList[4].min_Temp,
                        londonList[4].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(5),
                        londonList[5].weather_State_Name,
                        londonList[5].weather_State_Abbr,
                        londonList[5].min_Temp,
                        londonList[5].max_Temp
                    )
                ),
                WeatherModel(WeatherModel.CITY_INFO, CityInfo("CHICAGO"), null),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time,
                        chicagoList[0].weather_State_Name,
                        chicagoList[0].weather_State_Abbr,
                        chicagoList[0].min_Temp,
                        chicagoList[0].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(1),
                        chicagoList[1].weather_State_Name,
                        chicagoList[1].weather_State_Abbr,
                        chicagoList[1].min_Temp,
                        chicagoList[1].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(2),
                        chicagoList[2].weather_State_Name,
                        chicagoList[2].weather_State_Abbr,
                        chicagoList[2].min_Temp,
                        chicagoList[2].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(3),
                        chicagoList[3].weather_State_Name,
                        chicagoList[3].weather_State_Abbr,
                        chicagoList[3].min_Temp,
                        chicagoList[3].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(4),
                        chicagoList[4].weather_State_Name,
                        chicagoList[4].weather_State_Abbr,
                        chicagoList[4].min_Temp,
                        chicagoList[4].max_Temp
                    )
                ),
                WeatherModel(
                    WeatherModel.WEATHER_INFO,
                    null,
                    WeatherInfo(
                        time.plusDays(5),
                        chicagoList[5].weather_State_Name,
                        chicagoList[5].weather_State_Abbr,
                        chicagoList[5].min_Temp,
                        chicagoList[5].max_Temp
                    )
                ),
            )
            val adapter = WeatherAdapter(list)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)) //구분선 추가

            viewModel.weatherLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
// coroutine 통해 순차적으로 받기
// 날씨에 따라 받아오기
// room 사용하기
