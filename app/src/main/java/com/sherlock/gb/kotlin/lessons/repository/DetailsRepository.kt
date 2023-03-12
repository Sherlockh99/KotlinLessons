package com.sherlock.gb.kotlin.lessons.repository

import com.sherlock.gb.kotlin.lessons.viewmodel.DetailsViewModel

interface DetailsRepository {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback)
}