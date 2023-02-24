package com.sherlock.gb.kotlin.lessons.lesson1

open class PersonCopy constructor(val name: String = "defaultName", var age: Int = 20){

    var testParamNoNull: Int = 1
    var testParamNull: Int? = null
    lateinit var s: String

    fun test(testParam:String = "dfsdfsdfs"){
        var temp:Int = testParamNoNull?:0
        alex(temp)
    }

    fun alex(s: Int){

    }
}