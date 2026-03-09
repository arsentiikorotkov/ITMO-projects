package com.web.room

import androidx.room.*

@Dao
interface MessagesDAO {
    @Query("SELECT * FROM messages")
    fun getAllMessages(): List<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(messageEntity: MessageEntity)

    @Query("DELETE FROM messages")
    fun deleteAll()

    @Delete
    fun delete(messageEntity: MessageEntity)
}