package com.sherlock.gb.kotlin.lessons.lesson3

import android.util.Log
import android.view.ViewGroup
import com.sherlock.gb.kotlin.lessons.lesson1.Person
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

    override fun f(string: String, i: Int):Double { //3.1 способ
        Log.d("@@@", "Сообщение $string")
        return 1.0
    }

    val callback = object: Speakable{ //3.2 способ
        override fun f(string: String, i: Int):Double {
            //this@Lesson3

            Log.d("@@@", "Сообщение $string")
            return 1.0
        }
    }

    //4.1 способ
    val callbackLambda1 = Speakable { string, i ->
        Log.d("@@@", "Сообщение $string")
        //return@Speakable 1.0
        1.0
    }

    //4.2 способ
    val callbackLambda2 = r@{ string:String, i: Int ->
        Log.d("@@@", "Сообщение $string")
        Log.d("@@@", "Сообщение $string")
        Log.d("@@@", "Сообщение $string")
        Log.d("@@@", "Сообщение $string")
        //return@r 1.0
        1.0
    }

    fun test(){
        val people:List<Person?> = mutableListOf(Person("name1,20"), Person("name2",22))
        people.get(0)?.apply {
            age = 1
        }
        people[0]?.apply {
            age = 1
        }

        people.size
        val peopleHack = people.toMutableList()
        //val peopleAge : List<Person> = people.filter { it.age > 20 }


        val arr1 = arrayOf(1,2,3,4,5,6,7,8,9,10)
        val arr2 = arr1.map { it*2 }
        peopleHack.add(Person("name1",10))


        write(1)
        write(10.0f)
        write(10.0)
        write(10.0)
        write("")

        writeAll(1)
        writeAll(10.0f)
        writeAll(10.0)
        writeAll(10.0)
        writeAll("")
    }

    class Producer<out T>{
        private val content = mutableListOf<T>()
        fun test(param: String): T{
            return content.last()
        }

        fun test2():Int{
            return 1
        }
    }

    class Consumer<in T>{
        fun test(params: T){

        }
    }

    fun <T> writeAll(i: T){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Int){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Double){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Byte){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Boolean){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Person){
        Log.d("@@@", "Это $i")
    }

    fun write(i: String){
        Log.d("@@@", "Это $i")
    }

    fun write(i: Float){
        Log.d("@@@", "Это $i")
    }
}

fun<T: ViewGroup> someViewGroup(i: T){
    Log.d("@@@", "Это $i")
}

interface Flyable{
    val i: Int
    fun test()
    fun test2()
    fun test3(){
        Log.d("@@@","$i")
    }
}