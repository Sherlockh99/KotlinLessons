package com.sherlock.gb.kotlin.lessons.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.viewmodel.MainViewModel

class MainFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
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

        /**
         * создаём Observer, который по триггеру срабатывает и выполняет что-то
         * (событие onChanged)
         */
        val observer = object:Observer<Any>{
            override fun onChanged(data: Any) {
                renderData(data)
            }
        }

        /**
         * Уважаемая viewModel,верни свою liveData
         * Я в ней сейчас скажу ей скажу, слушай внимательно до тех пор слушай пока мой viewLifecycleOwner
         * т.е. viewLifecycleOwner - это некий объект внутри фрагмента, который за счёт  LifecycleOwner знает жив ли фрагмент
         *
         * т.е. я говорю liveData (которая сидит во viewModel (viewModel.getData())), подпиши меня как слушателя (observe()) , ориентируясь на мой жизненный цикл,
         * т.е. я слушаю, пока существую (viewLifecycleOwner) и жду от тебя callback (observer)
         * при callback будет вызываться onChanged из observer
         */
        viewModel.getData().observe(viewLifecycleOwner,observer)

        viewModel.getWeather()
    }

    private fun renderData(data:Any){
        Log.d("data",data.toString())
        Toast.makeText(requireContext(),"data",Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
