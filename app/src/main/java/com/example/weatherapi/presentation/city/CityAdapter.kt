package com.example.weatherapi.presentation.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.R
import com.example.weatherapi.data.model.Day
import com.example.weatherapi.databinding.WeatherDayItemBinding
import com.example.weatherapi.utils.setImage


class CityAdapter() : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private val days = mutableListOf<Day>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.weather_day_item, parent, false)
        )
    }

    fun setData(dayList: List<Day>) {
        days.clear()
        days.addAll(dayList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = days.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(days[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = WeatherDayItemBinding.bind(view)
        private val date = binding.date
        private val minWeather = binding.minWeather
        private val maxWeather = binding.maxWeather
        private val description = binding.description
        private val image = binding.image

        fun bind(day: Day) {
            date.text = day.applicableDate
            description.text = day.weatherStateName
            minWeather.text = itemView.context.getString(R.string.minWeather, day.minTemp)
            maxWeather.text = itemView.context.getString(R.string.maxWeather, day.maxTemp)
            image.setImage(day.weatherStateAbbr)
        }
    }
}