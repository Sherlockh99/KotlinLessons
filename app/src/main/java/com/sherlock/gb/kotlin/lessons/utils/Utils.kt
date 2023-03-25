package com.sherlock.gb.kotlin.lessons.utils

import com.sherlock.gb.kotlin.lessons.domain.room.HistoryEntity
import com.sherlock.gb.kotlin.lessons.repository.City
import com.sherlock.gb.kotlin.lessons.repository.Weather
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO

const val KEY_BUNDLE_WEATHER = "weather"
const val WEATHER_DOMAIN = "https://api.weatherapi.com"
const val WEATHER_ENDPOINT = "/v1/forecast.json"
const val WEATHER_KEY = "key"
const val KEY_BUNDLE_ACTIVITY_MESSAGE = "key1"
const val KEY_BUNDLE_SERVICE_MESSAGE = "key2"
const val KEY_BUNDLE_SERVICE_BROADCAST_WEATHER = "weather_s_b"
const val KEY_WAVE_SERVICE_BROADCAST = "myaction_way"
const val KEY_WAVE = "myaction"
const val KEY_BUNDLE_LAT = "lat1"
const val KEY_BUNDLE_LON = "lon1"
const val WEATHER_QUERY = "q"
const val WEATHER_LANG = "lang"
const val KEY_SP_FILE_NAME_1 = "file_name_1"
const val KEY_SP_FILE_NAME_IS_WORLD = "file_name_1_is_world"

fun convertDtoToModel(weatherDTO: WeatherDTO):Weather{
    return Weather(
        City(weatherDTO.location.name,weatherDTO.location.lat,weatherDTO.location.lon),
        weatherDTO.current.tempC.toInt(),
        weatherDTO.current.feelslikeC.toInt(),
        weatherDTO.current.condition.icon)
}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>):List<Weather>{
    return entityList.map{
        Weather(City(it.city,0.0,0.0),it.temperature,it.feelsLike,it.icon) //TODO HW lat, lon
    }
}

fun convertWeatherToEntity(weather: Weather):HistoryEntity{
    return HistoryEntity(0,weather.city.name,weather.temperature,weather.feelsLike,weather.icon)
}