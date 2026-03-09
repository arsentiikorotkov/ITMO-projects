package com.web

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.databinding.ImageViewBinding
import com.web.room.MessageEntity
import com.web.room.MessagesDAO
import java.text.SimpleDateFormat
import java.util.*


class MessagesAdapter(
    private val myService: MyService,
    private val messagesDAO: MessagesDAO,
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
        fun bind(message: MessageEntity) {
            if (message.data.image != null) {
                root.setOnClickListener {
                    onClick("http://213.189.221.170:8008/img/" + message.data.image.link)
                }
            } else {
                root.setOnClickListener{}
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
                    if (message.data.image.path != "") {
                        if (message.bitmap != null) {
                            image.setImageBitmap(message.bitmap)
                        } else {
                            Util.loadImage(message.data.image.path) { res ->
                                myService.handler.post {
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
                            myService.getImage("http://213.189.221.170:8008/thumb/" + message.data.image.link) { bitmapThumb ->
                                message.data.image.path = Util.saveImage(root.context, bitmapThumb, message.id)
                                message.bitmap = bitmapThumb
                                messagesDAO.insertMessage(message)
                                myService.handler.post {
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

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(myService.listOfMessages[position])

    override fun getItemCount() = myService.listOfMessages.size
}
