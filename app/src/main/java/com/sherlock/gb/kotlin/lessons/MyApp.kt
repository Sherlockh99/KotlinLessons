package com.sherlock.gb.kotlin.lessons

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
                        .addMigrations(migration1_2)
                        .build()

                }else{
                    throw (java.lang.IllegalStateException("Что-то пошло не так при запуске"))
                }
            }
            return db!!.historyDao()
        }

        val migration1_2: Migration = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE history_table ADD COLUMN condition TEXT NOT NULL DEFAULT ''")
            }

        }
    }
}