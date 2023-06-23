package com.example.whatsapp_application.entities;

public class ChatRequest {
    private String username;

    public ChatRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
