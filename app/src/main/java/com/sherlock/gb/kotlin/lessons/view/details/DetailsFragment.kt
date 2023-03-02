package com.sherlock.gb.kotlin.lessons.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentDetailsBinding
import com.sherlock.gb.kotlin.lessons.repository.OnServerResponse
import com.sherlock.gb.kotlin.lessons.repository.Weather
import com.sherlock.gb.kotlin.lessons.repository.WeatherLoader
import com.sherlock.gb.kotlin.lessons.repository.xdto.WeatherDTO
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_WEATHER
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), OnServerResponse {

    private var _binding: FragmentDetailsBinding? = null
    private val binding:FragmentDetailsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            //renderData(it)
            //localWeather = it
            //Thread{
                WeatherLoader(this@DetailsFragment).loadWeather(it.city.lat,it.city.lon)
            //}.start()

        }
    }

    private fun renderData(weather: WeatherDTO){
        binding.apply {
            loadingLayout.visibility = View.GONE
            //cityName.text = localWeather.city.name
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

}
