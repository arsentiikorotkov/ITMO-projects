package com.web

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.web.room.Message


class MyService : Service() {
    var listOfMessages: List<Message> = mutableListOf()
    var lastId: Long = -1
    var lastMaxId: Long = -1

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        fun getService() = this@MyService
    }
}