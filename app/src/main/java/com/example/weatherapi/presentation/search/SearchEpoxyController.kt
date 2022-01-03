package com.example.weatherapi.presentation.search

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import com.airbnb.epoxy.EpoxyController
import com.example.weatherapi.R
import com.example.weatherapi.data.model.City
import com.example.weatherapi.databinding.SearchItemBinding
import com.example.weatherapi.utils.ViewBindingKotlinModel

class SearchEpoxyController(private val clickListener: (City) -> Unit) : EpoxyController() {

    var cities: List<City> = listOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    var searchText: String = ""
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        buildCity()
    }

    private fun buildCity(){
        cities.mapIndexed { index, city ->
            CityEpoxyModel(city, searchText) {
                clickListener.invoke(it)
            }.id("search_item$index").addTo(this)
        }
    }

    data class CityEpoxyModel(
        val city: City,
        val searchText: String,
        private val clickListener: (City) -> Unit
    ) : ViewBindingKotlinModel<SearchItemBinding>(R.layout.search_item) {
        override fun SearchItemBinding.bind() {
            root.setOnClickListener { clickListener.invoke(city) }

            val index = city.name.indexOf(searchText)
            cityName.text = SpannableStringBuilder(city.name).apply {
                if (index > 0) {
                    setSpan(
                        StyleSpan(Typeface.BOLD),
                        index,
                        index + searchText.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
    }
}