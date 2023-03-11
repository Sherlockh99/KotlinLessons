package com.sherlock.gb.kotlin.lessons.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO
import com.sherlock.gb.kotlin.lessons.utils.WEATHER_DOMAIN
import com.sherlock.gb.kotlin.lessons.utils.WEATHER_KEY
import com.sherlock.gb.kotlin.lessons.utils.WEATHER_ENDPOINT
import com.sherlock.gb.kotlin.lessons.viewmodel.ResponseState
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse,
                    private val onErrorListener: OnServerResponseListener) {

    fun loadWeather(lat: Double, lon: Double){
        val urlText = "$WEATHER_DOMAIN$WEATHER_ENDPOINT?q=$lat,$lon&lang=ru"
        val uri = URL(urlText)

        //создаем соединение
        val urlCollection: HttpsURLConnection =
            (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000 //сколько ждать подключения; под капотом set
                readTimeout = 1000 //сколько ждать ответа
                addRequestProperty(WEATHER_KEY,"68e96e5766d44c9a91f11329231102")
            }

        Thread{
            try{
                val headers = urlCollection.headerFields
                val responseCode = urlCollection.responseCode
                val responseMessage = urlCollection.responseMessage

                val serverSide = 500..599 //диапазон ошибок на стороне сервера
                val clientSide = 400..499 //диапазон ошибок на стороне клиента
                val responseOK = 200..299

                when (responseCode) {
                    in serverSide -> {
                        Handler(Looper.getMainLooper()).post{
                            onErrorListener.onError(ResponseState.ServerSide)}
                    }
                    in clientSide -> {
                        Handler(Looper.getMainLooper()).post{
                            onErrorListener.onError(ResponseState.ClientSide(responseCode))}
                    }
                    in responseOK -> {
                        //получаем и поместим в буфер сайт
                        val buffer = BufferedReader(InputStreamReader(urlCollection.inputStream))
                        val weatherDTO: WeatherDTO = Gson().fromJson(buffer,WeatherDTO::class.java)

                        /**
                         * получим указатель на главный поток (Handler)
                         * через его управляющего (Looper.getMainLooper())
                         * и ему туда добавили новую задачу
                         * (onServerResponseListener.onResponse(weatherDTO))
                         */
                        Handler(Looper.getMainLooper()).post{
                            onServerResponseListener.onResponse(weatherDTO)}
                    }
                }
            }catch (e: JsonSyntaxException){
                Log.e("Error get weather",e.toString())
            }finally {
                urlCollection.disconnect()
            }
        }.start()
    }
}