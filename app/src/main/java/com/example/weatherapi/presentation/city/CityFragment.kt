package com.example.weatherapi.presentation.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapi.App
import com.example.weatherapi.R
import com.example.weatherapi.data.model.WeatherCity
import com.example.weatherapi.databinding.FragmentCityBinding
import com.example.weatherapi.presentation.search.SearchEpoxyController
import com.example.weatherapi.utils.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: CityViewModel
    private lateinit var binding: FragmentCityBinding

    private val epoxyController = CityEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.component.inject(this)
        setupRecyclerView()
        setupObservers()
        val cityId = arguments?.getInt(Constants.CITY_ID)!!

        viewModel.getCity(cityId)
    }

    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner){
            onCityLoad(it)
        }
    }

    private fun setupRecyclerView() {
        binding.weatherRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    private fun onCityLoad(weatherCity: WeatherCity) {
        binding.cityName.text = weatherCity.title
        epoxyController.days = weatherCity.consolidated_weather
    }
}