package com.web

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.databinding.ImageViewBinding
import com.web.retrofit.MyApp
import com.web.room.Message
import com.web.room.MessagesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class MessagesAdapter(
    private val myService: MyService,
    private val messagesDAO: MessagesDAO,
    private val scope: CoroutineScope,
    private val onClick: (String) -> Unit
) :
    RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_view, parent, false)
        )
    }

    inner class MessageViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        private var binding = ImageViewBinding.bind(root)
        fun bind(message: Message) {
            if (message.data.image != null) {
                root.setOnClickListener {
                    onClick(message.data.image.link)
                }
            } else {
                root.setOnClickListener {}
            }

            with(binding) {
                val simpleDateFormat = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
                val newFrom: String = message.from
                val newText: String = simpleDateFormat.format(message.time.toLong())
                var newContent = ""
                from.text = newFrom
                time.text = newText
                if (message.data.text != null) {
                    newContent += message.data.text.text
                    content.text = newContent
                    image.visibility = View.INVISIBLE
                } else if (message.data.image != null) {
                    content.text = newContent
                    val path = message.data.image.path
                    if (path != null) {
                        if (message.bitmap != null) {
                            image.setImageBitmap(message.bitmap)
                        } else {
                            scope.launch(Dispatchers.IO) {
                                val res = Util.loadImage(path)
                                withContext(Dispatchers.Main) {
                                    if (res == null) {
                                        image.setImageResource(R.drawable.example_image)
                                    } else {
                                        message.bitmap = res
                                        image.setImageBitmap(res)
                                    }
                                }
                            }
                        }
                        image.visibility = View.VISIBLE
                    } else {
                        if (!Util.isOnline(root.context)) {
                            image.setImageResource(R.drawable.example_image)
                            image.visibility = View.VISIBLE
                        } else {
                            image.visibility = View.VISIBLE
                            scope.launch(Dispatchers.IO) {
                                val responseBody =
                                    MyApp.instance.api.getImageThumb(message.data.image.link).body()
                                if (responseBody == null) {
                                    withContext(Dispatchers.Main) {
                                        image.setImageResource(R.drawable.example_image)
                                    }
                                } else {
                                    val bitmapThumb =
                                        BitmapFactory.decodeStream(responseBody.byteStream())
                                    message.data.image.path =
                                        Util.saveImage(root.context, bitmapThumb, message.id)
                                    message.bitmap = bitmapThumb
                                    messagesDAO.insertMessage(message)
                                    withContext(Dispatchers.Main) {
                                        if (bitmapThumb == null) {
                                            image.setImageResource(R.drawable.example_image)
                                        } else {
                                            image.setImageBitmap(bitmapThumb)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(myService.listOfMessages[position])

    override fun getItemCount() = myService.listOfMessages.size
}
