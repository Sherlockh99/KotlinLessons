package com.sherlock.gb.kotlin.lessons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherlock.gb.kotlin.lessons.repository.*

class HistoryViewModel (
    private val liveData : MutableLiveData<AppState> = MutableLiveData(),
    private val repository : DetailsRepositoryRoomImpl = DetailsRepositoryRoomImpl()
): ViewModel(){

    /**
     * liveData - объект, который хранит данные.
     * На него можно подписаться и получать уведомления,
     * если данные, которые хранит liveData, изменились (см. MainFragment - object:Observer<Any>)
     */

    fun getData():LiveData<AppState> = liveData

    fun getAll(){
        repository.getAllWeatherDetails(object : CallbackForAll{
            override fun onResponse(listWeather: List<Weather>) {
                liveData.postValue(AppState.Success(listWeather))
            }

            override fun onFail() {

            }

        })
    }

    interface CallbackForAll {
        fun onResponse(listWeather: List<Weather>)
        fun onFail()
    }
}