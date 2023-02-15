package com.sherlock.gb.kotlin.lessons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel (private val liveData : MutableLiveData<AppState> = MutableLiveData()): ViewModel(
){
    /**
     * liveData - объект, который хранит данные.
     * На него можно подписаться и получать уведомления,
     * если данные, которые хранит liveData, изменились (см. MainFragment - object:Observer<Any>)
     */

    fun getData() = liveData

    fun getWeather(){
        Thread{
            liveData.postValue(AppState.Loading)
            sleep(2000L)
            if((0..10).random()>5) {
                /**
                 * liveData может изменяться синхронно и асинхронно
                 * синхронно (value) - это обновление в том же потоке, что и выполняется.
                 * В данном случае так нельзя.
                 * Необходимо обновление в главном потоке (асинхронно), используя метод postValue()
                 */
                liveData.postValue(AppState.Success(Any()))
            }else{
                liveData.postValue(AppState.Error(IllegalAccessException()))
            }
        }.start()
    }
}