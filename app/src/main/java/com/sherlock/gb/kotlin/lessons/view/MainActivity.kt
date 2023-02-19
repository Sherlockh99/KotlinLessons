package com.sherlock.gb.kotlin.lessons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.view.main.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }

    /*
    val t = 1
    val any:Any = t
    val object1:Objects = t
    val any1:Any = object1
    */
}