package com.sherlock.gb.kotlin.lessons.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val extra = it.getStringExtra("key2")
            Log.d("@@@","MyBroadcastReceiver onReceive $extra")
        }

    }

}