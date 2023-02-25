package com.sherlock.gb.kotlin.lessons.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentDetailsBinding
import com.sherlock.gb.kotlin.lessons.repository.Weather
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_WEATHER
import kotlinx.android.synthetic.main.fragment_details.*

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            renderData(it)
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

}
