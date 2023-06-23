package com.example.whatsapp_application.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loginDetails")
public class LoginDetail {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;


    public LoginDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

}
