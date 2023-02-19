package com.sherlock.gb.kotlin.lessons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sherlock.gb.kotlin.lessons.repository.RepositoryImpl

class MainViewModel (
    private val liveData : MutableLiveData<AppState> = MutableLiveData(),
    private val repository : RepositoryImpl = RepositoryImpl()
): ViewModel(
){
    /**
     * liveData - объект, который хранит данные.
     * На него можно подписаться и получать уведомления,
     * если данные, которые хранит liveData, изменились (см. MainFragment - object:Observer<Any>)
     */

    fun getData() = liveData

    fun getWeatherRussia() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

    private fun getWeather(isRussian: Boolean){
        Thread{
            liveData.postValue(AppState.Loading)

            if((0..10).random()>0) {

                val answer = if(isRussian) repository.getRussianWeatherFromLocalStorage() else repository.getWorldWeatherFromLocalStorage()

                /**
                 * liveData может изменяться синхронно и асинхронно
                 * синхронно (value) - это обновление в том же потоке, что и выполняется.
                 * В данном случае так нельзя.
                 * Необходимо обновление в главном потоке (асинхронно), используя метод postValue()
                 */
                liveData.postValue(AppState.Success(answer))
            }else{
                liveData.postValue(AppState.Error(IllegalAccessException()))
            }
        }.start()
    }
}