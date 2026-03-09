package com.web.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Message::class, MessageForSendingOffline::class])
abstract class MessagesDb : RoomDatabase() {
    abstract fun getMessageDAO(): MessagesDAO
    abstract fun getMessageForSendingDAO(): MessagesForSendingDAO
}