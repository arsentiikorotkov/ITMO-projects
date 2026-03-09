package com.web

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


class MyService : Service() {

    var listOfMessages: List<Message> = mutableListOf()
    var listOfThreads = mutableListOf<Thread>()
    val handler = Handler(Looper.getMainLooper())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        fun getService() = this@MyService
    }

    fun getJson(url: String, onFinished: (String) -> Unit) {
        Log.i("ooo", "loadingJson")
        val thread  = Thread {
            try {
                val httpUrlConnection = URL(url).openConnection() as HttpURLConnection
                val streamReader = InputStreamReader(httpUrlConnection.inputStream)
                val json = streamReader.readText()
                httpUrlConnection.disconnect()
                onFinished(json)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        thread.start()
        listOfThreads.add(thread)
    }

    fun getImage(link: String?, onFinished: (Bitmap?) -> Unit) {
        if (link == null) {
            onFinished(null)
        } else {
            val thread  = Thread {
                try {
                    val httpUrlConnection = URL(link).openConnection() as HttpURLConnection
                    val bitmap = BitmapFactory.decodeStream(httpUrlConnection.inputStream)
                    httpUrlConnection.disconnect()
                    onFinished(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    onFinished(null)
                }
            }
            thread.start()
            listOfThreads.add(thread)
        }
    }

    fun sendMessage(message: String, onFinished: () -> Unit) {
        Thread {
            try {
                val httpUrlConnection = URL("http://213.189.221.170:8008/1ch").openConnection() as HttpURLConnection
                httpUrlConnection.doOutput = true
                httpUrlConnection.doInput = true
                httpUrlConnection.requestMethod = "POST"
                httpUrlConnection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )
                val outputStream: OutputStream = httpUrlConnection.outputStream
                outputStream.write("{\"from\":\"arsentiikorotkov\",\"data\":{\"Text\":{\"text\":\"$message\"}}}".toByteArray())
                Log.i("unlucky", httpUrlConnection.responseCode.toString())
                httpUrlConnection.disconnect()
                onFinished()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
}