package com.web.room

import androidx.room.*

@Dao
interface MessagesDAO {
    @Query("SELECT * FROM messages")
    fun getAllMessages(): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<Message>)

    @Query("DELETE FROM messages")
    fun deleteAll()

    @Delete
    fun delete(message: Message)
}