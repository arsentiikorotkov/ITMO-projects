package com.web.room

import android.graphics.Bitmap
import androidx.room.*
import com.squareup.moshi.Json


class Data(
    @Embedded @field:Json(name = "Text") val text: Text?,
    @Embedded @field:Json(name = "Image") val image: Image?
) {
    class Text(@field:Json(name = "text") val text: String)
    class Image(@field:Json(name = "link") val link: String, var path: String? = null)
}

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey @field:Json(name = "id") val id: Long,
    @ColumnInfo @field:Json(name = "from") val from: String,
    @ColumnInfo @field:Json(name = "to") val to: String,
    @Embedded @field:Json(name = "data") val data: Data,
    @ColumnInfo @field:Json(name = "time") val time: String
) {
    @Transient
    var bitmap: Bitmap? = null
}