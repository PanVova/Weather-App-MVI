package com.example.weatherapi.presentation.city

import com.airbnb.epoxy.EpoxyController
import com.example.weatherapi.R
import com.example.weatherapi.data.model.Day
import com.example.weatherapi.databinding.WeatherDayItemBinding
import com.example.weatherapi.utils.ViewBindingKotlinModel
import com.example.weatherapi.utils.setImage

class CityEpoxyController() : EpoxyController() {

    var days: List<Day> = listOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        buildDays()
    }

    private fun buildDays() {
        days.forEachIndexed { index, day ->
            DayEpoxyModel(day).id("day_item$index").addTo(this)
        }
    }

    data class DayEpoxyModel(
        val city: Day,
    ) : ViewBindingKotlinModel<WeatherDayItemBinding>(R.layout.weather_day_item) {
        override fun WeatherDayItemBinding.bind() {
            with(city) {
                date.text = applicableDate
                description.text = weatherStateName

                with(root.context) {
                    minWeather.text = getString(R.string.minWeather, minTemp)
                    maxWeather.text = getString(R.string.maxWeather, maxTemp)
                }

                image.setImage(weatherStateAbbr)
            }
        }
    }
}