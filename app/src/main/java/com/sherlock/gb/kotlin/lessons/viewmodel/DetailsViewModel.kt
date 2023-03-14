package com.sherlock.gb.kotlin.lessons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherlock.gb.kotlin.lessons.repository.*

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    //private val repository: DetailsRepository = DetailsRepositoryOkHttpImpl()
    private val repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
):ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City){
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city, object : Callback {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
            }
        })
    }

    fun interface Callback {
        fun onResponse(weather: Weather)
        //TODO HW Fail
    }
}