package com.sherlock.gb.kotlin.lessons.lesson4

import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sherlock.gb.kotlin.lessons.R
import com.sherlock.gb.kotlin.lessons.lesson1.Person
import com.sherlock.gb.kotlin.lessons.lesson3.Lesson3
import com.sherlock.gb.kotlin.lessons.view.MainActivity

class Lesson4 {

    //1 способ
    lateinit var lesson3: Lesson3
    fun some1(){
        lesson3.usual1("some произошло")
    }

    val pr = 777

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

    private val person: Person? = Person("testName",20)

    fun main(mainActivity: MainActivity){
        funHigh(_f, __f, _c)

        Log.d("@@@","До $person")
        //если t не null, то мы попадаем в функцию let (внутрь {})
        val newPersonLet = person?.let {
            it.age = 10
            it.name = "LetName"
            1
        }
        Log.d("@@@","После let $person")

        val newPersonRun = person?.run {
            //this.age = 10
            //this.name = "ddd"
            age = 99
            name = "RunName"

            this@Lesson4.pr

            2
        }

        Log.d("@@@","После run $person")

        val newPersonAlso = person?.also {
            it.age = 55
            it.name = "AlsoName"
        }
        Log.d("@@@","После also $person")

        val newPersonApply = person?.apply {
            age = 66
            name = "ApplyName"
            4
        }
        Log.d("@@@","После apply $person")

        Log.d("@@@","Результат Let $newPersonLet")
        Log.d("@@@","Результат Run $newPersonRun")
        Log.d("@@@","Результат Also $newPersonAlso")
        Log.d("@@@","Результат Apply $newPersonApply")

        with(person!!){
            age = 33
            name = "WithName"
        }
        // 1ый способ некорректный)
        val textView = TextView(mainActivity)
        textView.text = "ddddd"
        textView.textSize = 30f

        mainActivity.findViewById<ConstraintLayout>(R.id.layout).addView(textView)

        // 2ой способ корректный)
        mainActivity.findViewById<ConstraintLayout>(R.id.layout).addView(TextView(mainActivity).apply {
            text = "ddddd"
            textSize = 30f
        })


    }

    //val t:String? = null


    fun was(){
        Log.d("@@@","Не был на уроке")
    }
}