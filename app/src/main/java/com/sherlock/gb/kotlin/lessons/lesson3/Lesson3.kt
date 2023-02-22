package com.sherlock.gb.kotlin.lessons.lesson3

import android.util.Log

class Lesson3 {
    fun usual1(string: String){ //1 способ
        Log.d("@@@", "Сообщение $string")
        //"some произошло"
    }

    val f = fun (string: String){ //2 способ
        //что выполняется и мы можем передавать/вызывать что-то из f
        Log.d("@@@", "Сообщение $string")
        //"some произошло"
    }

}