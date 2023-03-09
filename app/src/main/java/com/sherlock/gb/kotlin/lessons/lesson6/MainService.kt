package com.sherlock.gb.kotlin.lessons.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.sherlock.gb.kotlin.lessons.utils.KEY_BUNDLE_SERVICE_MESSAGE
import com.sherlock.gb.kotlin.lessons.utils.KEY_WAVE

class MainService(val name: String=""):IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","Work MainService")
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_ACTIVITY_MESSAGE)
            Log.d("@@@","Work MainService $extra")
            //заснем на секунду и отправим бродкаст нашему бродкасту
            Thread.sleep(1000L)
            val message = Intent(KEY_WAVE)
            message.putExtra(KEY_BUNDLE_SERVICE_MESSAGE, " привет активити, и тебе всего хорошего")
            sendBroadcast(message)

            //LocalBroadcastManager.getInstance(this).sendBroadcast(message) - если был зарегистрирован локально
        }
    }


}