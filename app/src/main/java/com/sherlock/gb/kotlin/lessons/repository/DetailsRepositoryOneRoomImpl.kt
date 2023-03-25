package com.sherlock.gb.kotlin.lessons.repository

import com.sherlock.gb.kotlin.lessons.MyApp
import com.sherlock.gb.kotlin.lessons.utils.convertHistoryEntityToWeather
import com.sherlock.gb.kotlin.lessons.utils.convertWeatherToEntity
import com.sherlock.gb.kotlin.lessons.viewmodel.DetailsViewModel
import com.sherlock.gb.kotlin.lessons.viewmodel.HistoryViewModel

class DetailsRepositoryRoomImpl: DetailsRepositoryOne,DetailsRepositoryAll,DetailsRepositoryAdd {
    override fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll) {
        callback.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
    }

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        callback.onResponse(list.last())
    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))
    }


}