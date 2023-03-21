package com.sherlock.gb.kotlin.lessons

import android.app.Application
import androidx.room.Room
import com.sherlock.gb.kotlin.lessons.domain.room.HistoryDao
import com.sherlock.gb.kotlin.lessons.domain.room.MyDB

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object{
        private var db: MyDB? = null
        private var appContext:MyApp? = null
        fun getHistoryDao():HistoryDao{
            if(db==null){
                if(appContext!=null){
                    db = Room.databaseBuilder(appContext!!,MyDB::class.java,"test")
                        .allowMainThreadQueries()  //TODO HW сделать в отдельном потоке
                        .build()

                }else{
                    throw (java.lang.IllegalStateException("Что-то пошло не так при запуске"))
                }
            }
            return db!!.historyDao()
        }
    }
}