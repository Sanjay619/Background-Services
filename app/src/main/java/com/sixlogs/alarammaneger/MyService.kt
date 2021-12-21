package com.sixlogs.alarammaneger

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class MyService : Service() {

    private val TAG = "MyService"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startThread()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startThread() {
        thread {
            while (true){
                Log.d(TAG , "service is running..!")
                Thread.sleep(2000)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}