package com.example.whatsapp_application.entities;

import java.util.ArrayList;

public class DetailedChat {
    private String id;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public DetailedChat(String id, User user1, User user2, ArrayList<Message> messages) {
        this.id = id;
        this.users.add(new User(user1.getUsername(),user1.getDisplayName(),user1.getProfilePic()));
        this.users.add(new User(user2.getUsername(),user2.getDisplayName(),user2.getProfilePic()));
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
