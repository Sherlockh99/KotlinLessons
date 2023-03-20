package com.sherlock.gb.kotlin.lessons.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.WorkManager
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3
import com.sherlock.gb.kotlin.lessons.lesson4.BaseImpl
import com.sherlock.gb.kotlin.lessons.lesson4.BossDelegate
import com.sherlock.gb.kotlin.lessons.lesson4.Lesson4
import com.sherlock.gb.kotlin.lessons.lesson6.MainService
import com.sherlock.gb.kotlin.lessons.lesson6.MyBroadcastReceiver
import com.sherlock.gb.kotlin.lessons.lesson6.ThreadsFragment
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.sherlock.gb.kotlin.lessons.utils.KEY_SP_FILE_NAME_1
import com.sherlock.gb.kotlin.lessons.utils.KEY_SP_FILE_NAME_IS_WORLD
import com.sherlock.gb.kotlin.lessons.utils.KEY_WAVE
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

        startService(Intent(this,MainService::class.java).apply {
            putExtra(KEY_BUNDLE_ACTIVITY_MESSAGE,"Привет сервис")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter(KEY_WAVE)) //на весь мир
        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))

        //WorkManager позволяет добавлять в очередь, даже при закрытом приложении (на Samsung работает, на Xiaomi - нет)
        //WorkManager.getInstance(this).enqueue()

        //только на приложение (локально). Использовать или выше или это. Что-то одно
        /**
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver, IntentFilter("myaction"))
        */


        /**
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
        */

        val sp = getSharedPreferences(KEY_SP_FILE_NAME_1,Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean(KEY_SP_FILE_NAME_IS_WORLD,true)
        editor.apply()

        val defaulValueIsWorld = true
        sp.getBoolean(KEY_SP_FILE_NAME_IS_WORLD,defaulValueIsWorld)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_threads ->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, ThreadsFragment.newInstance())
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
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