package com.web.activity

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.web.MyService
import com.web.R
import com.web.Util
import com.web.databinding.HighResBinding


class HiResActivity : AppCompatActivity() {

    private lateinit var binding: HighResBinding

    private var myService: MyService? = null

    private var isBound = false

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: MyService.MyBinder = service as MyService.MyBinder
            myService = binderBridge.getService()
            isBound = true

            val serv = myService
            if (serv != null) {
                serv.getImage(intent.getStringExtra("url")) { bitmapImage ->
                    serv.handler.post {
                        if (bitmapImage != null) binding.imageView.setImageBitmap(bitmapImage)
                        else binding.imageView.setImageResource(R.drawable.example_image)
                    }
                }
            } else {
                binding.imageView.setImageResource(R.drawable.example_image)
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            myService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HighResBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Util.isOnline(applicationContext)) {
            val intent = Intent(this, MyService::class.java)
            val url = getIntent().getStringExtra("image")
            intent.putExtra("url", url)
            startService(intent)
            bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
        } else  {
            Util.missingConnection(applicationContext)
            binding.imageView.setImageResource(R.drawable.example_image)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(boundServiceConnection)
            isBound = !isBound
        }
    }
}