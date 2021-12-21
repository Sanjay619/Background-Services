package com.sixlogs.alarammaneger

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*









class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }



    // test
    fun onServiceStart(view: android.view.View) {
        setAlaramManeger()
    }

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent
    fun setAlaramManeger(){

        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, MyReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }

// Set the alarm to start at 8:30 a.m.
        val calendar: Calendar = Calendar.getInstance().apply {
           // timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 55)
            set(Calendar.SECOND, 0)
        }

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
        alarmMgr?.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
       /* alarmMgr?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000 * 60 * 20,
            alarmIntent
        )*/
    }
}