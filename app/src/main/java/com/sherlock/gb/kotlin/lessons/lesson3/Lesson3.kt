package com.sherlock.gb.kotlin.lessons.lesson3

import android.util.Log
import com.sherlock.gb.kotlin.lessons.lesson4.Speakable

class Lesson3: Speakable {

    fun usual1(string: String){ //1 способ
        Log.d("@@@", "Сообщение $string")
        //"some произошло"
    }

    val f = fun (string: String){ //2 способ
        //что выполняется и мы можем передавать/вызывать что-то из f
        Log.d("@@@", "Сообщение $string")
        //"some произошло"
    }

    override fun f(string: String) { //3.1 способ
        Log.d("@@@", "Сообщение $string")
    }

    val callback = object: Speakable{ //3.2 способ
        override fun f(string: String) {
            Log.d("@@@", "Сообщение $string")
        }
    }

    //4.1 способ
    val callbackLambda1 = Speakable { string -> Log.d("@@@", "Сообщение $string") }

    //4.2 способ
    val callbackLambda2 = { string:String -> Log.d("@@@", "Сообщение $string") }
}