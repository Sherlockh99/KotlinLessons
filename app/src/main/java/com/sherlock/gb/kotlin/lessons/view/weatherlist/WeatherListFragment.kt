package com.sherlock.gb.kotlin.lessons.view.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentWeatherListBinding
import com.sherlock.gb.kotlin.lessons.viewmodel.AppState
import com.sherlock.gb.kotlin.lessons.viewmodel.MainViewModel

class WeatherListFragment : Fragment() {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding:FragmentWeatherListBinding
        get(){
            return _binding!!
        }

    val adapter = WeatherListAdapter()

    var isRussian = true

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
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * ViewModelProvider (хранилище) запоминает MainViewModel::class.java,
         * если даже активити была пересоздана
         * ViewModelProvider, посмотри, есть ли у тебя уже созданная MainViewModel
         * Если её не существует, то он её создаст и вернёт
         * Если же существует (фрагмент пересоздался, например),
         * то вернёт уже ранее созданную MainViewModel со всеми сохраненными в ней данными
         */
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter

        /**
         * создаём Observer, который по триггеру срабатывает и выполняет что-то
         * (событие onChanged)
         */
        val observer = object:Observer<AppState>{
            override fun onChanged(data: AppState) {
                renderData(data)
            }
        }

        /**
         * Уважаемая viewModel,верни свою liveData (viewModel.getData())
         * и подпиши меня как слушателя (observe()),
         * ориентируясь на мой жизненный цикл (viewLifecycleOwner)
         *
         * т.е. viewLifecycleOwner - это некий объект внутри фрагмента,
         * который за счёт LifecycleOwner знает жив ли фрагмент
         *
         * т.е. я говорю liveData (которая сидит во viewModel (viewModel.getData())),
         * подпиши меня как слушателя (observe()), ориентируясь на мой жизненный цикл,
         *
         * т.е. я слушаю, пока существую (viewLifecycleOwner) и жду от тебя callback (observer)
         * при callback будет вызываться onChanged из observer
         */
        viewModel.getData().observe(viewLifecycleOwner,observer)

        binding.floatingActionButton.setOnClickListener{
            isRussian = !isRussian
            if(isRussian){
                viewModel.getWeatherRussia()
                binding.floatingActionButton.setImageResource(R.drawable.ic_earth)
            }else{
                viewModel.getWeatherWorld()
                binding.floatingActionButton.setImageResource(R.drawable.ic_russia)
            }
        }
        /**
         * постучим в свою viewModel и запросим у него getWeather()
         * дальше сработает триггер onChanged после того, как будет обновлена liveData в MainViewModel
         */
        viewModel.getWeatherRussia()
    }

    private fun renderData(data:AppState){
        when (data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                val s = "Do not work"
                Snackbar.make(binding.root,s,Snackbar.LENGTH_LONG).show()

            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)
                val s = "Work"
                Snackbar.make(binding.root,s,Snackbar.LENGTH_LONG).show()
            }
        }
    }

/**
    private fun setData(weatherData: List<Weather>) {

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
 */

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }
}
