package com.web.room

import android.graphics.Bitmap
import androidx.room.*


class Data(
    @Embedded val image: Image?,
    @Embedded val text: Text?,
) {
    class Image(val link: String, var path: String)
    class Text(val text: String)
}

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo val from: String,
    @ColumnInfo val time: String,
    @Embedded val data: Data,
) {
    @Ignore
    var bitmap: Bitmap? = null
}