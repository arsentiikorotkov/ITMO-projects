package com.web

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.web.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONTokener


class MainActivity : AppCompatActivity() {

    lateinit var myService: MyService
    private var lastId: Int = -1
    private var lastMaxId: Int = -1
    private var isBound = false
    private lateinit var binding: ActivityMainBinding

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: MyService.MyBinder = service as MyService.MyBinder
            myService = binderBridge.getService()
            isBound = true

            updateMessages(10000, 100)

            binding.messageButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    if (binding.messageField.text.toString().isNotEmpty()) {
                        myService.sendMessage(
                            binding.messageField.text.toString()
                        ) { myService.handler.post { binding.refreshButton.callOnClick() } }
                        binding.messageField.setText("")
                    }
                } else Util.missingConnection(applicationContext)
            }

            binding.refreshButton.setOnClickListener {
                Log.i("hhh", lastId.toString())
                if (Util.isOnline(applicationContext)) {
                    clear()
                    updateMessages(10000, 100)
                }
                else Util.missingConnection(applicationContext)
            }

            binding.upButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    clear()
                    Log.i("hhh", lastId.toString())
                    if (lastId + 100 >= lastMaxId) binding.refreshButton.callOnClick()
                    else updateMessages(lastId + 100, 100)
                } else Util.missingConnection(applicationContext)
            }

            binding.downButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    clear()
                    Log.i("hhh", lastId.toString())
                    if (lastId - 100 <= 101) updateMessages(101, 100)
                    else updateMessages(lastId - 100, 100)
                } else Util.missingConnection(applicationContext)
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!Util.isOnline(applicationContext)) {
            Util.missingConnection(applicationContext)
        }
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("url", "http://213.189.221.170:8008/1ch")
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(boundServiceConnection)
            isBound = !isBound
        }
    }

    private fun getListOfMessages(json: String): List<Message> {
        val tokener = JSONTokener(json)
        val jsonArray = JSONArray(tokener)
        val messages = mutableListOf<Message>()
        lastId = jsonArray.getJSONObject(0).getInt("id")
        if (lastId > lastMaxId) lastMaxId = lastId
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val data = jsonObject.getJSONObject("data")
            val message = if (data.has("Text")) {
                Message(
                    jsonObject.getString("from"),
                    Content.Text(data.getJSONObject("Text").getString("text")),
                    jsonObject.getString("time")
                )
            } else {
                val link = data.getJSONObject("Image").getString("link")
                Message(
                    jsonObject.getString("from"),
                    Content.Image(link, null),
                    jsonObject.getString("time")
                )
            }
            messages.add(message)
        }
        return messages
    }


    private fun updateMessages(lastKnownId: Int, limit: Int) {
        if (myService.listOfMessages.isEmpty()) {
            Log.i("ooo", "1")
            myService.getJson(
                "http://213.189.221.170:8008/1ch?lastKnownId=$lastKnownId&reverse=true&limit=$limit"
            ) { json ->
                myService.listOfMessages = getListOfMessages(json)
                Handler(mainLooper).post {
                    fillRecyclerView()
                }
            }
        } else {
            Log.i("ooo", "2")
            fillRecyclerView()
        }
    }

    private fun fillRecyclerView() {
        binding.myRecyclerView.apply {
            adapter = MessagesAdapter(myService) {
                val intent = Intent(this@MainActivity, HiResActivity::class.java)
                intent.putExtra("url", it)
                startActivity(intent)
            }
        }
    }

    private fun clear() {
        myService.listOfMessages = mutableListOf()
        myService.listOfThreads.forEach{it.interrupt()}
        myService.listOfThreads.forEach { thread ->
            try {
                thread.join()
            } catch (ignored: InterruptedException) {
            }
        }
        myService.listOfThreads = mutableListOf()
    }
}