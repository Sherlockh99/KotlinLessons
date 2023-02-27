package com.sherlock.gb.kotlin.lessons.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sherlock.gb.kotlin.lessons.databinding.ActivityMainWebviewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {
    lateinit var binding: ActivityMainWebviewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener {
            val urlText = binding.etTextUrl.text.toString()

            /*** вариант 1, детальный ***/
            val uri = URL(urlText)

            //создаем соединение
            val urlCollection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000 //сколько ждать подключения; под капотом set
                readTimeout = 1000 //сколько ждать ответа

            }

            Thread{
                val headers = urlCollection.headerFields
                //получаем и поместим в буфер сайт
                val buffer = BufferedReader(InputStreamReader(urlCollection.inputStream))
                val result = getLinesAsOneBigText(buffer)
                //binding.webview.loadUrl(urlText) - можно так, кратко

                //обращаемся к стартовому потоку, т.к. только с шлавного потока можно обращаться  кwebview
                /** 1 вариант
                runOnUiThread{
                    binding.webview.loadData(result,"text/html; utf-8","utf-8")
                    binding.webview.loadDataWithBaseURL(result,"text/html; utf-8","utf-8")
                }
                **/
                /** 2 вариант
                //"под капотом", второй вариант вызова = аналог первому
                **/
                Handler(Looper.getMainLooper()).post{
                    binding.webview.settings.javaScriptEnabled = true
                    binding.webview.loadDataWithBaseURL(
                        null,
                        result,
                        "text/html; utf-8",
                        "utf-8",
                        null)
                }


            }.start()

            /**** варинат 2 ***/
            //binding.webview.loadUrl(urlText)
            /****/

        }
    }

    fun getLinesAsOneBigText(bufferedReader: BufferedReader):String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}