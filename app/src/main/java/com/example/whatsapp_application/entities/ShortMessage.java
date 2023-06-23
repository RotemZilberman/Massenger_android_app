package com.example.whatsapp_application.entities;

public class ShortMessage {
    private String id;
    private String created;
    private String content;

    public ShortMessage(String id, String created, String content) {
        this.id = id;
        this.created = created;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
