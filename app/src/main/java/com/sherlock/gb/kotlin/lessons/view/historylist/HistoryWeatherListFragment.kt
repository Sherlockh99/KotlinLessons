package com.sherlock.gb.kotlin.lessons.view.historylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentHistoryWeatherListBinding
import com.sherlock.gb.kotlin.lessons.databinding.FragmentWeatherListBinding
import com.sherlock.gb.kotlin.lessons.repository.Weather
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_WEATHER
import com.sherlock.gb.kotlin.lessons.view.details.DetailsFragment
import com.sherlock.gb.kotlin.lessons.viewmodel.AppState
import com.sherlock.gb.kotlin.lessons.viewmodel.HistoryViewModel
import com.sherlock.gb.kotlin.lessons.viewmodel.MainViewModel

class HistoryWeatherListFragment : Fragment() {

    private var _binding: FragmentHistoryWeatherListBinding? = null
    private val binding:FragmentHistoryWeatherListBinding
        get(){
            return _binding!!
        }

    private val adapter = HistoryWeatherListAdapter()

    /**
     * viewModel будет инициилизована лямдой при первом обращении к ней,
     * до этого viewModel = null
     */
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
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
        _binding = FragmentHistoryWeatherListBinding.inflate(inflater, container, false)

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
        //val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /** 1 вариант
        binding.recyclerView.apply {
            adapter = this@WeatherListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        */

        /** 2 вариант **/
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }



        /**
         * создаём Observer, который по триггеру срабатывает и выполняет что-то
         * (событие onChanged)
         */
        /*
        val observer = object:Observer<AppState>{
            override fun onChanged(data: AppState) {
                renderData(data)
            }
        }
        */
        val observer = {data: AppState -> renderData(data)}

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

        /**
         * постучим в свою viewModel и запросим у него getWeather()
         * дальше сработает триггер onChanged после того,
         * как будет обновлена liveData в MainViewModel
         */
        viewModel.getAll()
    }


    private fun renderData(data:AppState){
        when (data){
            is AppState.Error -> {
                //binding.loadingLayout.visibility = View.GONE
                val s = "Do not work"
                Snackbar.make(binding.root,s,Snackbar.LENGTH_LONG).show()

            }
            is AppState.Loading -> {
                //binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                //binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)
                val s = "Work"
                Snackbar.make(binding.root,s,Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryWeatherListFragment()
    }

}
