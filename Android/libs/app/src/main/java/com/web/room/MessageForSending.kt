package com.web.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "messages_for_sending")
 class MessageForSendingOffline(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @Embedded val data: Data,
)

class MessageForSendingImage(@field:Json(name = "from") val from: String)

class MessageForSendingText(
    @field:Json(name = "from") val from: String,
    @field:Json(name = "data") val data: DataText,
)

class DataText(@field:Json(name = "Text") val text: Data.Text)
