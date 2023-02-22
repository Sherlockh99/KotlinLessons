package com.sherlock.gb.kotlin.lessons.lesson4

import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3

class Lesson4 {
    lateinit var lesson3: Lesson3 //1 способ
    fun some1(){
        lesson3.usual1("some произошло")
    }

    var f = fun (string: String){}
    fun some2(){
        f("Что-то произошло, 2 способ")
    }

}