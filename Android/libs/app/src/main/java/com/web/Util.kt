package com.web

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import com.web.room.MessagesDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

class Util {
    companion object {
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
            return false
        }

        fun saveImage(applicationContext: Context, bitmap: Bitmap?, id: Long): String? {
            if (bitmap == null) {
                Log.i("storage","Default $id")
                return null
            }
            val root = applicationContext.cacheDir.toString()
            val dirForImages = File("$root/myApp/images")
            if (!dirForImages.exists()){
                Log.i("storage","Creating dir")
                if (!dirForImages.mkdirs()) {
                    Log.i("storage","Error creating dir for $id")
                    return null
                }
            }
            val nameOfImage = "image-$id.png"
            val imageFile = File(dirForImages, nameOfImage)
            if (imageFile.exists()) imageFile.delete()
            try {
                val out = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, out)
                out.flush()
                out.close()
                Log.i("storage","Save $id accepted")
                return "$root/myApp/images/$nameOfImage"
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.i("storage","Save $id error")
            return null
        }

        suspend fun loadImage(path: String): Bitmap? {
            return try {
                val imageFile = File(path)
                val bitmap = withContext(Dispatchers.IO) {
                    BitmapFactory.decodeStream(FileInputStream(imageFile))
                }
                Log.i("storage", "Loading accepted")
                bitmap
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Log.i("storage", "Loading error")
                null
            }
        }

        fun deleteImages(messagesDAO: MessagesDAO) {
            val listOfMessages = messagesDAO.getAllMessages()
            for (i in listOfMessages.indices) {
                val mesId = listOfMessages[i].id
                val img = listOfMessages[i].data.image
                if (img != null) {
                    val path = img.path
                    if (path == null) {
                        Log.i("storage", "$mesId don't exist")
                    } else {
                        val imageFile = File(path)
                        if (imageFile.exists()) {
                            Log.i("storage", "try to delete $mesId")
                            imageFile.delete()
                            if (!imageFile.exists()) {
                                Log.i("storage", "delete accepted")
                            }
                        } else {
                            Log.i("storage", "$mesId don't exist")
                        }
                    }
                }
                messagesDAO.delete(listOfMessages[i])
            }
        }

        fun missingConnection(context: Context) {
            Toast.makeText(
                context,
                R.string.missingConnection,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun messageForSending(context: Context) {
            Toast.makeText(
                context,
                R.string.messageForSending,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun showResponseCode(context: Context, responseCode: Int) {
            if (responseCode != 200) {
                Toast.makeText(
                    context,
                    "Sorry, there seems to be an error №$responseCode",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}