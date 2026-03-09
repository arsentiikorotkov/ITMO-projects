package com.web

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web.databinding.ImageViewBinding
import java.text.SimpleDateFormat
import java.util.*


class MessagesAdapter(
    private val myService: MyService,
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
            if (message.data is Content.Image) {
                root.setOnClickListener {
                    onClick("http://213.189.221.170:8008/img/" + message.data.link)
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
                if (message.data is Content.Text) {
                    newContent += message.data.text
                    content.text = newContent
                    image.visibility = View.INVISIBLE
                } else if (message.data is Content.Image) {
                    content.text = newContent
                    if (message.data.bitmapThumb != null) {
                        Log.i("jjj", message.from)
                        image.setImageBitmap(message.data.bitmapThumb)
                        image.visibility = View.VISIBLE
                    } else {
                        if (!Util.isOnline(root.context)) {
                            image.setImageResource(R.drawable.example_image)
                            image.visibility = View.VISIBLE
                        } else {
                            myService.getImage("http://213.189.221.170:8008/thumb/" + message.data.link) { bitmapThumb ->
                                message.data.bitmapThumb = bitmapThumb
                                myService.handler.post {
                                    if (bitmapThumb == null) {
                                        image.setImageResource(R.drawable.example_image)
                                    } else {
                                        image.setImageBitmap(message.data.bitmapThumb)
                                    }
                                    image.visibility = View.VISIBLE
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
