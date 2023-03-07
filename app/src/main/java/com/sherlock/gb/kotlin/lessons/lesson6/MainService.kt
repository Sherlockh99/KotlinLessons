package com.sherlock.gb.kotlin.lessons.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainService(val name: String=""):IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","Work MainService")
        intent?.let {
            val extra = it.getStringExtra("key1")
            Log.d("@@@","Work MainService $extra")
            //заснем на секунду и отправим бродкаст нашему бродкасту
            Thread.sleep(1000L)
            val message = Intent("myaction")
            message.putExtra("key2", " привет активити, и тебе всего хорошего")
            sendBroadcast(message)

            //LocalBroadcastManager.getInstance(this).sendBroadcast(message) - если был зарегистрирован локально
        }
    }


}