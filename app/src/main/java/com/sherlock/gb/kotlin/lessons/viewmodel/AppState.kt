package com.sherlock.gb.kotlin.lessons.viewmodel

import com.sherlock.gb.kotlin.lessons.repository.Weather

sealed class AppState {
    object Loading : AppState()
    data class Success(val weatherList: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
}