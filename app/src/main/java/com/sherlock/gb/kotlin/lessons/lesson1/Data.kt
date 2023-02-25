package com.sherlock.gb.kotlin.lessons.lesson1

import android.util.Log

class Data {
}

object Database{
    fun getTestCycle(){
        val list = listOf(1,2,3,4,5,6,7)
        for(one in list){
            Log.d("@@@","$one getTestCycle")
        }
        with(list){
            forEach{
                Log.d("@@@","$it getTestCycle")
            }

            forEach{ one: Int->
                Log.d("@@@","$one getTestCycle")
            }
        }

        repeat(10){
            Log.d("@@@","$it getTestCycle")
        }

        val test = 1..10
        for(i in test){
            Log.d("@@@","$i getTestCycle")
        }

        for(i in 1 until 10 step 2){
            Log.d("@@@","$i getTestCycle")
        }

        for(i in 1 until 10 step 3){
            Log.d("@@@","$i getTestCycle")
        }

        var counter = 10
        while (counter-->0){
            Log.d("@@@","$counter getTestCycle")
        }

        counter = 10
        do{
            Log.d("@@@","$counter getTestCycle")
        } while (counter-->0)

        for(i in 1 until 10 step 3){
            Log.d("@@@","$i getTestCycle")
        }
    }

    fun getTestIf(): String{
        val result = if(0==0){
            val f12 = 1 + 235423
            val f2 = 1 + 235423
            val f3 = 1 + 235423
            "test"
        }else{
            "Мир сошел с ума"
        }

        try{

        }catch (e:Throwable){

        }finally {

        }
        return result
    }

    fun getTestWhen(input: TestEnum):String{
        val result = when (input){
            TestEnum.test1 -> {
                val f12 = 1 + 235423
                val f2 = 1 + 235423
                val f3 = 1 + 235423
                "1"
            }
            TestEnum.test2 -> "2"
            TestEnum.test3 -> "3"
            TestEnum.test4 -> "4"
        }
        return result
    }
}