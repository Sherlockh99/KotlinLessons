package com.sherlock.gb.kotlin.lessons.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L) //эмуляция запроса на сервер
        return Weather() // эмуляция ответа
    }

    override fun getWorldWeatherFromLocalStorage(): List<Weather> {
        return getWorldCities() //эмуляция ответа
    }

    override fun getWeatherFromLocalStorage(): List<Weather> {
        return getRussianCities()
    }
}