package com.sherlock.gb.kotlin.lessons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3
import com.sherlock.gb.kotlin.lessons.lesson4.BaseImpl
import com.sherlock.gb.kotlin.lessons.lesson4.BossDelegate
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

        with(lesson4){
            //1 способ
            this.lesson3 = lesson3
            some1()

            //2 способ
            f = lesson3.f
            some2()

            //3.1 способ
            speakable = lesson3  //ссылается на реализацию в классе lesson3 (обрезает всё лишнее)
            some3()

            //3.2 способ
            speakable = lesson3.callback
            some4()

            //4.1 способ
            speakable = lesson3.callbackLambda1
            some5()

            /*
            почему-то выдает ошибку
            //4.2 способ
            lesson4.speakable = lesson3.callbackLambda2
            lesson4.some6()
            */

            //4.2 способ

            some6 { string, i  ->
                Log.d("@@@","Сообщение $string")
                1.0   }

            was()
            //main(this@MainActivity)
        }

        val worker = BaseImpl()
        BossDelegate(worker,worker).apply {
            manipulate()
        }
    }


    fun Lesson4.was(){
        Log.d("@@@","Был на уроке $pr")
    }

    /*
    val t = 1
    val any:Any = t
    val object1:Objects = t
    val any1:Any = object1
    */
    /**
     * 3:07:00
     */
}