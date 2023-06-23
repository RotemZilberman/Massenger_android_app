package com.example.whatsapp_application.entities;

public class CompressChat {
    String id;
    User user;

    public CompressChat(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Chat toChat(){
        return new Chat(this.getId(),this.getUser());
    }
}
