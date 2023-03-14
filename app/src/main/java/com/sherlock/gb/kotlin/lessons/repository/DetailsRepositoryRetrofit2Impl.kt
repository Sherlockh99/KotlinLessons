package com.sherlock.gb.kotlin.lessons.repository

import com.google.gson.GsonBuilder
import com.sherlock.gb.kotlin.lessons.BuildConfig
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO
import com.sherlock.gb.kotlin.lessons.utils.WEATHER_DOMAIN
import com.sherlock.gb.kotlin.lessons.utils.convertDtoToModel
import com.sherlock.gb.kotlin.lessons.viewmodel.DetailsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl: DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val weatherAPI = Retrofit.Builder().apply {
            baseUrl(WEATHER_DOMAIN)
            //GsonBuilder().setLenient().create() - чтобы читал даже битый JSON
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java)

        //weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY,city.lat,city.lon).execute() - можно так
        val q: String = city.lat.toString() + "," + city.lon.toString()
        weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY,q,"ru").enqueue(object : Callback<WeatherDTO>{
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        callback.onResponse(convertDtoToModel(it))
                    }
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}