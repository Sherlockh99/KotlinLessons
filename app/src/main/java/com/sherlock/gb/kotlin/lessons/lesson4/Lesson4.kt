package com.sherlock.gb.kotlin.lessons.lesson4

import android.util.Log
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
        speakable.f("Что-то произошло, 4.1 способ",5)
    }

    //4.2 способ
    fun some6(_speakable: Speakable){
        _speakable.f("Что-то произошло, 4.2 способ",6)
    }

    val _f = fun (float: Float){

    }

    val __f = fun (double: Double):Short{
        return 1
    }

    val _c = fun (char: Char):Boolean{
        return true
    }


    private fun funHigh(_f:(float:Float)-> Unit,
                __f:(double:Double)->Short,
                _c:(char:Char)->Boolean){

        _f(1f)
        val short = __f(1.0)
        val boolean = _c('f')
    }

    fun main(){
        funHigh(_f, __f, _c)
    }

    fun was(){
        Log.d("@@@","Не был на уроке")
    }
}