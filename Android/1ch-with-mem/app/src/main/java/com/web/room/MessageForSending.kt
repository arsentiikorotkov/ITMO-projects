package com.web.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "messages_for_sending")
 class MessageForSending(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @Embedded val data: Data,
)
