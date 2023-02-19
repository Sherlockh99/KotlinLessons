package com.sherlock.gb.kotlin.lessons.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sherlock.gb.kotlin.lessons.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

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

/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun renderData(data:AppState){
        when (data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                val s = "Do not work"
                Snackbar.make(binding.mainView,s,Snackbar.LENGTH_LONG).show()

            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                setData(data.weatherData)
                val s = "Work"
                Snackbar.make(binding.mainView,s,Snackbar.LENGTH_LONG).show()
            }
        }
    }



    private fun setData(weatherData: Weather) {
        binding.apply {
            cityName.text = weatherData.city.name
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
            )
            temperatureValue.text = weatherData.temperature.toString()
            feelsLikeValue.text = weatherData.feelsLike.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
 */
}
