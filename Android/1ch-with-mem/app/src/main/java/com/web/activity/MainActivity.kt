package com.web.activity

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.web.MessagesAdapter
import com.web.MyService
import com.web.R
import com.web.Util
import com.web.databinding.ActivityMainBinding
import com.web.room.*
import org.json.JSONArray
import org.json.JSONTokener


class MainActivity : AppCompatActivity() {

    lateinit var myService: MyService
    private var lastId: Int = -1
    private var lastMaxId: Int = -1
    private var isBound = false
    private lateinit var db: MessagesDb
    private lateinit var messagesDAO: MessagesDAO
    private lateinit var messagesForSendingDAO: MessagesForSendingDAO
    private lateinit var binding: ActivityMainBinding

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImage = data!!.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                if (Util.isOnline(applicationContext)) {
                    myService.sendMessage(filePath, false) { myService.handler.post { binding.refreshButton.callOnClick() } }
                } else {
                    Util.messageForSending(applicationContext)
                    Thread {
                        messagesForSendingDAO.insertMessageForSending(
                            MessageForSending(
                                0,
                                Data(Data.Image(filePath, ""), null)
                            )
                        )
                    }.start()
                }
            }
        }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: MyService.MyBinder = service as MyService.MyBinder
            myService = binderBridge.getService()
            isBound = true

            if (!Util.isOnline(applicationContext)) {
                Util.missingConnection(applicationContext)
            }

            updateMessages(10000)

            binding.messageButton.setOnClickListener {
                val data = binding.messageField.text.toString()
                if (Util.isOnline(applicationContext)) {
                    if (data.isNotEmpty()) {
                        myService.sendMessage(
                            data, true
                        ) { myService.handler.post { binding.refreshButton.callOnClick() } }
                        binding.messageField.setText("")
                    }
                } else if (data.isNotEmpty()) {
                    Util.messageForSending(applicationContext)
                    Thread {
                        messagesForSendingDAO.insertMessageForSending(
                            MessageForSending(
                                0,
                                Data(null, Data.Text(data))
                            )
                        )
                    }.start()
                    binding.messageField.setText("")
                } else {
                    Util.missingConnection(applicationContext)
                }
            }

            binding.imageButton.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) !=
                    PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        0
                    )
                } else {
                    val i = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI
                    )
                    resultLauncher.launch(i)
                }
            }

            binding.refreshButton.setOnClickListener {
                Log.i("hhh", lastId.toString())
                if (Util.isOnline(applicationContext)) {
                    clear()
                    updateMessages(10000)
                } else Util.missingConnection(applicationContext)
            }

            binding.upButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    clear()
                    if (lastId + 100 >= lastMaxId) binding.refreshButton.callOnClick()
                    else updateMessages(lastId + 100)
                } else Util.missingConnection(applicationContext)
            }

            binding.downButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    clear()
                    if (lastId - 100 <= 101) updateMessages(101)
                    else updateMessages(lastId - 100)
                } else Util.missingConnection(applicationContext)
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val i = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI
                    )
                    resultLauncher.launch(i)
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.permissionNotReceived,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, MessagesDb::class.java, "messages").build()
        messagesDAO = db.getMessageDAO()
        messagesForSendingDAO = db.getMessageForSendingDAO()

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

    private fun getListOfMessages(json: String): List<MessageEntity> {
        val tokener = JSONTokener(json)
        val jsonArray = JSONArray(tokener)
        val messages = mutableListOf<MessageEntity>()
        lastId = jsonArray.getJSONObject(0).getInt("id")
        if (lastId > lastMaxId) lastMaxId = lastId
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val data = jsonObject.getJSONObject("data")
            val message = if (data.has("Text")) {
                MessageEntity(
                    jsonObject.getLong("id"),
                    jsonObject.getString("from"),
                    jsonObject.getString("time"),
                    Data(null, Data.Text(data.getJSONObject("Text").getString("text")))
                )
            } else {
                val link = data.getJSONObject("Image").getString("link")
                MessageEntity(
                    jsonObject.getLong("id"),
                    jsonObject.getString("from"),
                    jsonObject.getString("time"),
                    Data(Data.Image(link, ""), null)
                )
            }
            messagesDAO.insertMessage(message)
            messages.add(message)
        }
        return messages
    }

    private fun updateMessages(lastKnownId: Int) {
        sendingMessages()

        if (myService.listOfMessages.isEmpty() && !Util.isOnline(applicationContext)) {
            Log.i("uploading", "data from bd")
            val thread = Thread {
                myService.listOfMessages =
                    messagesDAO.getAllMessages().reversed()
                Handler(mainLooper).post {
                    fillRecyclerView()
                }
            }
            thread.start()
            myService.listOfThreads.add(thread)
        } else if (myService.listOfMessages.isEmpty() && Util.isOnline(applicationContext)) {
            Log.i("uploading", "data from web")
            myService.getJson(
                "http://213.189.221.170:8008/1ch?lastKnownId=$lastKnownId&reverse=true&limit=100"
            ) { json ->
                Util.deleteImages(messagesDAO)
                myService.listOfMessages = getListOfMessages(json)
                Handler(mainLooper).post {
                    fillRecyclerView()
                }
            }
        } else {
            Log.i("uploading", "data from service")
            fillRecyclerView()
        }
    }

    private fun sendingMessages() {
        val thread = Thread {
            if (Util.isOnline(applicationContext) && messagesForSendingDAO.size() > 0) {
                val messagesForSending = messagesForSendingDAO.getAllMessagesForSending()
                for (i in messagesForSending.indices) {
                    val text = messagesForSending[i].data.text
                    val image = messagesForSending[i].data.image
                    if (text != null) {
                        myService.sendMessage(
                            text.text, true
                        ) { myService.handler.post { binding.refreshButton.callOnClick() } }
                    } else if (image != null) {
                        myService.sendMessage(image.link, false)
                        { myService.handler.post { binding.refreshButton.callOnClick() } }
                    }
                    messagesForSendingDAO.delete(messagesForSending[i])
                }
            }
        }
        thread.start()
        myService.listOfThreads.add(thread)
    }

    private fun fillRecyclerView() {
        binding.myRecyclerView.apply {
            adapter = MessagesAdapter(myService, messagesDAO) {
                val intent = Intent(this@MainActivity, HiResActivity::class.java)
                intent.putExtra("url", it)
                startActivity(intent)
            }
        }
    }

    private fun clear() {
        myService.listOfMessages = mutableListOf()
        myService.listOfThreads.forEach { it.interrupt() }
        myService.listOfThreads.forEach { thread ->
            try {
                thread.join()
            } catch (ignored: InterruptedException) {
            }
        }
        myService.listOfThreads = mutableListOf()
    }
}