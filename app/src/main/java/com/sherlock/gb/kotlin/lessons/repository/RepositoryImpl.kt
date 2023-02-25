package com.sherlock.gb.kotlin.lessons.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L) //эмуляция запроса на сервер
        return Weather() // эмуляция ответа
    }

    override fun getWorldWeatherFromLocalStorage() = getWorldCities() //эмуляция ответа

    override fun getRussianWeatherFromLocalStorage(): List<Weather> = getRussianCities()
}