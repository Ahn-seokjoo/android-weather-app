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
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.main.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

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

        val adapter = WeatherAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)) //구분선 추가

        //코루틴 시작 - 순서를 보장해줌
        lifecycleScope.launch {
            val seoulWeatherList = viewModel.weatherRepo.getWeatherAsync(SEOUL)
            val londonWeatherList = viewModel.weatherRepo.getWeatherAsync(LONDON)
            val chicagoWeatherList = viewModel.weatherRepo.getWeatherAsync(CHICAGO)

            val weatherList = seoulWeatherList + londonWeatherList + chicagoWeatherList
            adapter.submitList(weatherList)
        }

        //데이터 관찰
//        viewModel.weatherListLiveData.observe(viewLifecycleOwner){
//            adapter.submitList(it)
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}