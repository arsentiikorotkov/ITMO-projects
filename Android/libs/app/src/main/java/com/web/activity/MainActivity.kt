package com.web.activity

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.web.MessagesAdapter
import com.web.MyService
import com.web.R
import com.web.Util
import com.web.databinding.ActivityMainBinding
import com.web.retrofit.MyApp
import com.web.room.*
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

import java.io.FileInputStream


class MainActivity : AppCompatActivity() {
    lateinit var myService: MyService
    private var isBound = false
    private lateinit var messagesDAO: MessagesDAO
    private lateinit var messagesForSendingDAO: MessagesForSendingDAO
    lateinit var scope: CoroutineScope
    private lateinit var binding: ActivityMainBinding

    private val mediaIntent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Images.Media.INTERNAL_CONTENT_URI
    )

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
                    lifecycle.coroutineScope.launch(Dispatchers.IO) {
                        postImage(filePath)
                        binding.refreshButton.callOnClick()
                    }
                } else {
                    lifecycle.coroutineScope.launch(Dispatchers.IO) {
                        messagesForSendingDAO.insertMessageForSending(
                            MessageForSendingOffline(
                                0,
                                Data(null, Data.Image(filePath, ""))
                            )
                        )
                    }
                    Util.messageForSending(applicationContext)
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
                        lifecycle.coroutineScope.launch(Dispatchers.IO) {
                            postText(data)
                            binding.refreshButton.callOnClick()
                        }
                        binding.messageField.setText("")
                    }
                } else if (data.isNotEmpty()) {
                    Util.messageForSending(applicationContext)
                    lifecycle.coroutineScope.launch(Dispatchers.IO) {
                        messagesForSendingDAO.insertMessageForSending(
                            MessageForSendingOffline(
                                0,
                                Data(Data.Text(data), null)
                            )
                        )
                    }
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
                    resultLauncher.launch(mediaIntent)
                }
            }

            binding.refreshButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    scope.cancel()
                    myService.listOfMessages = mutableListOf()
                    updateMessages(10000)
                } else Util.missingConnection(applicationContext)
            }

            binding.upButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    scope.cancel()
                    myService.listOfMessages = mutableListOf()
                    if (myService.lastId + 100 >= myService.lastMaxId) binding.refreshButton.callOnClick()
                    else updateMessages(myService.lastId + 100)
                } else Util.missingConnection(applicationContext)
            }

            binding.downButton.setOnClickListener {
                if (Util.isOnline(applicationContext)) {
                    scope.cancel()
                    myService.listOfMessages = mutableListOf()
                    if (myService.lastId - 100 <= 101) updateMessages(101)
                    else updateMessages(myService.lastId - 100)
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
                    resultLauncher.launch(mediaIntent)
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

        messagesDAO = MyApp.instance.db.getMessageDAO()
        messagesForSendingDAO = MyApp.instance.db.getMessageForSendingDAO()

        val intent = Intent(this, MyService::class.java)
        intent.putExtra("url", "http://213.189.221.170:8008/1ch")
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
        if (isBound) {
            unbindService(boundServiceConnection)
            isBound = !isBound
        }
    }

    private fun updateMessages(lastKnownId: Long) {
        scope = CoroutineScope(Dispatchers.IO)
        scope.launch(Dispatchers.IO) {
            sendingMessages()
        }

        if (myService.listOfMessages.isEmpty() && !Util.isOnline(applicationContext)) {
            Log.i("uploading", "data from bd")
            scope.launch(Dispatchers.IO) {
                myService.listOfMessages = messagesDAO.getAllMessages().reversed()
                if (myService.listOfMessages.isNotEmpty()) {
                    myService.lastId = myService.listOfMessages[0].id
                    myService.lastMaxId = myService.lastId
                }
                withContext(Dispatchers.Main) {
                    fillRecyclerView()
                }
            }
        } else if (myService.listOfMessages.isEmpty() && Util.isOnline(applicationContext)) {
            Log.i("uploading", "data from web")
            scope.launch(Dispatchers.IO) {
                Util.deleteImages(messagesDAO)
                val response = MyApp.instance.api.getListMessages(lastKnownId, true, 100)
                withContext(Dispatchers.Main) {
                    Util.showResponseCode(applicationContext, response.code())
                }
                myService.listOfMessages = response.body() as List<Message>
                myService.lastId = myService.listOfMessages[0].id
                if (myService.lastId > myService.lastMaxId) myService.lastMaxId = myService.lastId
                messagesDAO.insertAll(myService.listOfMessages)
                withContext(Dispatchers.Main) {
                    fillRecyclerView()
                }
            }
        } else {
            Log.i("uploading", "data from service")
            fillRecyclerView()
        }
    }

    private suspend fun sendingMessages() {
        if (Util.isOnline(applicationContext) && messagesForSendingDAO.size() > 0) {
            val messagesForSending = messagesForSendingDAO.getAllMessagesForSending()
            for (i in messagesForSending.indices) {
                val text = messagesForSending[i].data.text
                val image = messagesForSending[i].data.image
                if (text != null) {
                    postText(text.text)
                } else if (image != null) {
                    postImage(image.link)
                }
                messagesForSendingDAO.delete(messagesForSending[i])
            }
            binding.refreshButton.callOnClick()
        }
    }

    private suspend fun postText(text: String) {
        val response = MyApp.instance.api.postText(
            MessageForSendingText(
                "arsentiikorotkov",
                DataText(Data.Text(text))
            )
        )
        withContext(Dispatchers.Main) {
            Util.showResponseCode(applicationContext, response.code())
        }
    }

    private suspend fun postImage(link: String) {
        val inputStream = withContext(Dispatchers.IO) {
            FileInputStream(File(link))
        }
        val response = MyApp.instance.api.postImage(
            MultipartBody.Part.createFormData(
                "picture", link, RequestBody.create(
                    MediaType.parse("image/jpeg"),
                    inputStream.readBytes()
                )
            ), MessageForSendingImage("arsentiikorotkov")
        )
        withContext(Dispatchers.Main) {
            Util.showResponseCode(applicationContext, response.code())
        }
    }

    private fun fillRecyclerView() {
        binding.myRecyclerView.apply {
            adapter = MessagesAdapter(myService, messagesDAO, scope) {
                val intent = Intent(this@MainActivity, HiResActivity::class.java)
                intent.putExtra("url", it)
                startActivity(intent)
            }
        }
    }
}