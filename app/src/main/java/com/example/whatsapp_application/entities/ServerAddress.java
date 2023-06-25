package com.example.whatsapp_application.entities;

public class ServerAddress {

    static private String address = "http://10.0.2.2:5000/";

    static public String getAddress() {
        return address;
    }

    static public void setAddress(String addr) {
        address = addr;
    }
}
