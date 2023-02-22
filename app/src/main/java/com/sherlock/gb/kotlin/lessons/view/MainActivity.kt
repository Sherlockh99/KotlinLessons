package com.sherlock.gb.kotlin.lessons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3
import com.sherlock.gb.kotlin.lessons.lesson4.Lesson4
import com.sherlock.gb.kotlin.lessons.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commit()
        }

        val lesson3 = Lesson3()
        val lesson4 = Lesson4()

        //1 способ
        lesson4.lesson3 = lesson3
        lesson4.some1()

        //2 способ
        lesson4.f = lesson3.f
        lesson4.some2()

        //3 способ
        lesson4.speakable = lesson3  //ссылается на реализацию в классе lesson3 (обрезает всё лишнее)
        lesson4.some3()

        //3 способ
        lesson4.speakable = lesson3.callback
        lesson4.some4()

    }

    /*
    val t = 1
    val any:Any = t
    val object1:Objects = t
    val any1:Any = object1
    */
}