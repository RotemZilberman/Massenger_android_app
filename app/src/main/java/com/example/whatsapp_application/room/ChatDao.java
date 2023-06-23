package com.example.whatsapp_application.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whatsapp_application.entities.Chat;

import java.util.List;

@Dao
public interface ChatDao {
    @Insert
    void insert(Chat chat);

    @Insert
    void insertAll(Chat... chats);

    @Query("SELECT * FROM chats")
    List<Chat> getAllChats();

    @Query("SELECT * FROM chats WHERE id = :chatId")
    Chat getChat(String chatId);

    @Query("DELETE FROM chats")
    void clear();
}

