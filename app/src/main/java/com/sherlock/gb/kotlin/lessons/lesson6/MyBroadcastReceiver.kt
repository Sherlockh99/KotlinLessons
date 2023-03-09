package com.sherlock.gb.kotlin.lessons.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_SERVICE_MESSAGE

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_SERVICE_MESSAGE)
            Log.d("@@@","MyBroadcastReceiver onReceive $extra")
        }

    }

}