package com.web.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.web.R
import com.web.Util
import com.web.databinding.HighResBinding
import com.web.retrofit.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HiResActivity : AppCompatActivity() {
    private lateinit var binding: HighResBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HighResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val link = intent.getStringExtra("url")
        if (link == null) {
            binding.imageView.setImageResource(R.drawable.example_image)
        } else if (Util.isOnline(applicationContext)) {
            lifecycle.coroutineScope.launch(Dispatchers.IO) {
                val response = MyApp.instance.api.getImageHigh(link)
                val responseBody = response.body()
                withContext(Dispatchers.Main) {
                    Util.showResponseCode(applicationContext, response.code())
                }
                if (responseBody == null) {
                    withContext(Dispatchers.Main) {
                        binding.imageView.setImageResource(R.drawable.example_image)
                    }
                } else {
                    val bitmapImage =
                        BitmapFactory.decodeStream(responseBody.byteStream())
                    withContext(Dispatchers.Main) {
                        if (bitmapImage != null) binding.imageView.setImageBitmap(bitmapImage)
                        else binding.imageView.setImageResource(R.drawable.example_image)
                    }
                }
            }
        } else {
            Util.missingConnection(applicationContext)
            binding.imageView.setImageResource(R.drawable.example_image)
        }
    }
}