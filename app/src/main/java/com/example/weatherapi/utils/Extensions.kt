package com.example.weatherapi.utils

import android.widget.ImageView
import com.example.weatherapi.R

fun ImageView.setImage(state: String) {
    val imageId = when (state) {
        "sn" -> R.drawable.icon_sn
        "sl" -> R.drawable.icon_sl
        "t" -> R.drawable.icon_t
        "hr" -> R.drawable.icon_hr
        "lr" -> R.drawable.icon_lr
        "s" -> R.drawable.icon_s
        "hc" -> R.drawable.icon_hc
        "lc" -> R.drawable.icon_lc
        "c" -> R.drawable.icon_c
        else -> R.drawable.icon_sn
    }
    setImageResource(imageId)
}