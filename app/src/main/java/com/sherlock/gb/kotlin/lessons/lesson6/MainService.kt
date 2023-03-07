package com.sherlock.gb.kotlin.lessons.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MainService(val name: String=""):IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","Work MainService")
    }


}