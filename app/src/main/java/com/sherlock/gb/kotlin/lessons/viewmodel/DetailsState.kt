package com.sherlock.gb.kotlin.lessons.viewmodel

import com.sherlock.gb.kotlin.lessons.repository.Weather

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val weather: Weather) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
}