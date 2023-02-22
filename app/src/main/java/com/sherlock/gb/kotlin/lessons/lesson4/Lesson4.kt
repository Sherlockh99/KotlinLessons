package com.sherlock.gb.kotlin.lessons.lesson4

import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3

class Lesson4 {

    //1 способ
    lateinit var lesson3: Lesson3
    fun some1(){
        lesson3.usual1("some произошло")
    }

    //2 сопсоб
    var f = fun (string: String){}
    fun some2(){
        f("Что-то произошло, 2 способ")
    }

    //3.1 способ
    lateinit var speakable: Speakable
    fun some3(){
        speakable.f("Что-то произошло, 3.1 способ",3)
    }

    //3.2 способ
    fun some4(){
        speakable.f("Что-то произошло, 3.2 способ",4)
    }

    //4.1 способ
    fun some5(){
        speakable.f("Что-то произошло, 3.2 способ",5)
    }

    //4.2 способ
    fun some6(_speakable: Speakable){
        _speakable.f("Что-то произошло, 3.2 способ",6)
    }

}