package com.sixlogs.alarammaneger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class MyReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action.equals(Intent.ACTION_BOOT_COMPLETED)){
            val Intent = Intent(context, ForgroundService::class.java)
            context.startForegroundService(Intent)
        }else{
            val Intent = Intent(context, ForgroundService::class.java)
            context.startForegroundService(Intent)
        }
    }
}