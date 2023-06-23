package com.example.whatsapp_application.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatsapp_application.room.Converters;

@Entity(tableName = "chats")
public class Chat {
    @PrimaryKey(autoGenerate = true)
    private int PrimaryKey;
    private String id;
    @TypeConverters(Converters.class)
    private User user;
    @TypeConverters(Converters.class)
    private Message lastMessage;

    public Chat(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public Message getLastMessage() {
        return lastMessage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getPrimaryKey() {
        return PrimaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        PrimaryKey = primaryKey;
    }
}

