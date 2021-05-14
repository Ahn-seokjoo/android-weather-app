package com.example.weatherapp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.weatherapp.main.viewmodel.WeatherViewModel

class MyFragmentFactory(private val viewModel: WeatherViewModel) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            MainFragment::class.java -> MainFragment(viewModel)
            else -> super.instantiate(classLoader, className)
        }
}