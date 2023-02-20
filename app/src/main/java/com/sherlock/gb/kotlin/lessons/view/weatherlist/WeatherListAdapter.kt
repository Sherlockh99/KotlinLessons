package com.sherlock.gb.kotlin.lessons.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.databinding.FragmentWeatherListRecyclerItemBinding
import com.sherlock.gb.kotlin.lessons.repository.Weather
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_WEATHER
import com.sherlock.gb.kotlin.lessons.view.MainActivity
import com.sherlock.gb.kotlin.lessons.view.details.DetailsFragment

class WeatherListAdapter(private var data: List<Weather> = listOf()):RecyclerView.Adapter<WeatherListAdapter.CityHolder>() {

    fun setData(dataNew:List<Weather>){
        this.data = dataNew
        notifyDataSetChanged() //TODO: DiffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size

    class CityHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(weather: Weather){
            val binding = FragmentWeatherListRecyclerItemBinding.bind(itemView)
            binding.tvCityName.text = weather.city.name

            binding.root.setOnClickListener{
                val bundle = Bundle()
                bundle.putParcelable(KEY_BUNDLE_WEATHER,weather)
                (itemView.context as MainActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container,DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commit()
            }

        }
    }
}