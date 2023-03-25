package com.sherlock.gb.kotlin.lessons.repository

import com.sherlock.gb.kotlin.lessons.viewmodel.HistoryViewModel

interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll)
}