package com.web.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessagesForSendingDAO {
    @Query("SELECT * FROM messages_for_sending")
    fun getAllMessagesForSending(): List<MessageForSending>

    @Insert
    fun insertMessageForSending(messageForSending: MessageForSending)

    @Query("DELETE FROM messages_for_sending")
    fun deleteAll()

    @Delete
    fun delete(messageForSending: MessageForSending)

    @Query("SELECT COUNT(*) FROM messages_for_sending")
    fun size(): Long
}