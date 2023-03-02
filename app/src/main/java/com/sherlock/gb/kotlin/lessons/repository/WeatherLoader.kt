package com.sherlock.gb.kotlin.lessons.repository

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat: Double, lon: Double){
        val urlText = "https://api.weatherapi.com/v1/forecast.json?q=$lat,$lon&lang=ru"
        val uri = URL(urlText)

        //создаем соединение
        val urlCollection: HttpsURLConnection =
            (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000 //сколько ждать подключения; под капотом set
                readTimeout = 1000 //сколько ждать ответа
                addRequestProperty("key","68e96e5766d44c9a91f11329231102")
            }
        Thread{
            try{
                val headers = urlCollection.headerFields
                val responseCode = urlCollection.responseCode
                //получаем и поместим в буфер сайт
                val buffer = BufferedReader(InputStreamReader(urlCollection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer,WeatherDTO::class.java)

                /**
                 * получим указатель на главный поток (Handler)
                 * через его управляющего (Looper.getMainLooper())
                 * и ему туда добавили новую задачу
                 * (onServerResponseListener.onResponse(weatherDTO))
                 */
                Handler(Looper.getMainLooper()).post{onServerResponseListener.onResponse(weatherDTO)}
            }catch (e: Exception){

            }finally {
                urlCollection.disconnect()
            }
        }.start()
    }
}