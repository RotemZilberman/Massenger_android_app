package com.example.whatsapp_application.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatsapp_application.activities.MyApplication;
import com.example.whatsapp_application.room.Converters;

@Entity(tableName = "messages")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int PrimaryKey;
    private String id;

    private String created;
    @TypeConverters(Converters.class)
    private User sender;

    private String content;
    private String chatId;

    public Message(String id, String chatId, User sender, String content, String created) {
        this.id = id;
        this.chatId = chatId;
        this.sender = sender;
        this.content = content;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public User getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isReceived() {
        return sender.getUsername().equals(MyApplication.getUser().getUsername());

    }
    public int getPrimaryKey() {
        return PrimaryKey;
    }
    public void setPrimaryKey(int primaryKey) {
        PrimaryKey = primaryKey;
    }
}
