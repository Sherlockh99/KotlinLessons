package com.sherlock.gb.kotlin.lessons.repository

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0)
