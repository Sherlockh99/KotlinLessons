package com.sherlock.gb.kotlin.lessons.view.weatherlist

import com.sherlock.gb.kotlin.lessons.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}