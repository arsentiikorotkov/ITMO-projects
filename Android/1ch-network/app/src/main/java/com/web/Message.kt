package com.web

import android.graphics.Bitmap

data class Message(val from: String, val data: Content, val time: String)

sealed class Content {
    class Image(val link: String, var bitmapThumb: Bitmap?) : Content()
    class Text(val text: String) : Content()
}