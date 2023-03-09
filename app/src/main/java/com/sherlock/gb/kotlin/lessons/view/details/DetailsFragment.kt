package com.sherlock.gb.kotlin.lessons.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.registerReceiver
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentDetailsBinding
import com.sherlock.gb.kotlin.lessons.repository.*
import com.sherlock.gb.kotlin.lessons.viewmodel.ResponseState
import kotlinx.android.synthetic.main.fragment_details.*
import com.google.android.material.snackbar.Snackbar
import com.sherlock.gb.kotlin.lessons.lesson6.MyBroadcastReceiver
import com.sherlock.gb.kotlin.lessons.utils.*


class DetailsFragment : Fragment(), OnServerResponse, OnServerResponseListener {
    lateinit var localWeather: Weather
    private var _binding: FragmentDetailsBinding? = null
    private val binding:FragmentDetailsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireContext().unregisterReceiver(receiver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * надуваем сгенерированный класс
         */
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //lateinit var localWeather : Weather
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,
            IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )

         */
        requireContext().registerReceiver(receiver,
            IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )

        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            localWeather = it

            requireActivity().startService(Intent(requireContext(),DetailsService::class.java).apply {
                putExtra(KEY_BUNDLE_LAT,localWeather.city.lat)
                putExtra(KEY_BUNDLE_LON,localWeather.city.lon)
            })

            //renderData(it)
            //localWeather = it
            //Thread{
            /*
                WeatherLoader(this@DetailsFragment,
                    this@DetailsFragment).loadWeather(it.city.lat,it.city.lon)
             */
            //}.start()
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {intent->
                intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let{
                    onResponse(it)
                }
            }
        }
    }

    private fun renderData(weather: Weather){
        binding.apply {
            loadingLayout.visibility = View.GONE
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weather.city.lat.toString(),
                weather.city.lon.toString()
            )
        }
    }

    private fun renderData(weather: WeatherDTO){
        binding.apply {
            loadingLayout.visibility = View.GONE
            cityName.text = weather.location.name
            temperatureValue.text = weather.current.tempC.toString()
            feelsLikeValue.text = weather.current.feelslikeC.toString()
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weather.location.lat.toString(),
                weather.location.lon.toString()
            )
        }
        //Snackbar.make(mainView,"Получилось",Snackbar.LENGTH_LONG).show() //TODO вынести в функцию расширения
        mainView.showSnackBar(mainView,"Получилось")
    }

    fun View.showSnackBar(view: View, text: String){
        Snackbar.make(view,text,Snackbar.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResponse(weatherDTO: WeatherDTO) {
        renderData(weatherDTO)
    }

    override fun onError(error: ResponseState) {
        when (error){
            is ResponseState.ServerSide ->{
                renderData(localWeather)
                Extensions.showToast(mainView,
                    "Ошибка на стороне сервера: $error. Отображены локальные данные")
            }
            is ResponseState.ClientSide ->
            {
                renderData(localWeather)
                Extensions.showToast(mainView,
                    "Ошибка на стороне клиента $error. Отображены локальные данные"
                )
            }
        }
    }
}
