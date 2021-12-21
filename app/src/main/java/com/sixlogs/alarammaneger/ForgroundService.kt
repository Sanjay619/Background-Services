package com.sixlogs.alarammaneger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class ForgroundService : Service() {
    private val TAG = "ForgroundService"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startThread()
        val CHANNEL_ID = "Forground service ID"


      val channel =   NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val builder = Notification.Builder(this, CHANNEL_ID)
            .setContentText("Service is running")
            .setContentTitle("Service start")
            .setSmallIcon(R.drawable.ic_launcher_background)
        startForeground(1001, builder.build())

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startThread() {
        thread {
            while (true) {

                checkTimer()
                Thread.sleep(1000 * 60)

               // val currentTime = getCurrentTime()
            }
        }
    }

    private fun checkTimer() {
        val currnetTime = getCurrentTime()
        val calendar: Calendar = Calendar.getInstance().apply {
            // timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR, 6)
            set(Calendar.MINUTE, 1)
        }
        //   val time = currnetTime.time
        val sdf = SimpleDateFormat("HH:mm")
        val savedMillisec = convertSecondsToHMmSs(calendar.timeInMillis)
        val savedTime = sdf.parse(savedMillisec)

        if(currnetTime == savedTime){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(0)
            }
            Log.d(TAG, "Service stopped..!")
            stopForeground(true)
            stopSelf()
        }else{
            Log.d(TAG, "service is running..!")
          //  Toast.makeText(this, "Time Not match", Toast.LENGTH_SHORT).show()
        }

    }
    private fun getCurrentTime(): Date {
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("HH:mm")
        val strDate = mdformat.format(calendar.time)

        return mdformat.parse(strDate)

    }
    fun convertSecondsToHMmSs(seconds: Long): String? {
        val date = Date(seconds)
        val formatter = SimpleDateFormat("HH:mm")
        val formatted = formatter.format(date)
        return formatted
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}