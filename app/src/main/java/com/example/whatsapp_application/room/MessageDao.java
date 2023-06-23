package com.example.whatsapp_application.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whatsapp_application.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Insert
    void insertAll(Message... messages);

    @Query("SELECT * FROM messages WHERE chatId = :chatId")
    List<Message> getAllMessages(String chatId);

    @Query("DELETE FROM messages WHERE chatId = :chatId")
    void clear(String chatId);

    @Query("DELETE FROM messages")
    void clearALL();

}
