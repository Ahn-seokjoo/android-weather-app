package com.example.weatherapp.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.main.view.adapter.WeatherAdapter
import com.example.weatherapp.main.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

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
        lifecycleScope.launch {
            viewModel.getWeather()
            val adapter = WeatherAdapter {
                Toast.makeText(
                    view.context,
                    "${it.weatherInfo?.date?.format(DateTimeFormatter.ofPattern("E dd MMM"))}의 날씨는 ${it.weatherInfo?.weatherStateName} 입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.weatherLiveData.observe(viewLifecycleOwner) {
                binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)) //구분선 추가
                binding.recyclerView.adapter = adapter
                WeatherAdapter.submitList(adapter, it)
            }
        }
    }
}
