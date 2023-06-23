package com.example.whatsapp_application.room;

import androidx.room.TypeConverter;

import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<User> fromString(String value) {
        Type listType = new TypeToken<ArrayList<User>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<User> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static Message messageFromString(String value) {
        return new Gson().fromJson(value, Message.class);
    }

    @TypeConverter
    public static String messageToString(Message message) {
        return new Gson().toJson(message);
    }

    @TypeConverter
    public static User profileFromString(String value) {
        return new Gson().fromJson(value, User.class);
    }

    @TypeConverter
    public static String profileToString(User profile) {
        return new Gson().toJson(profile);
    }
}
