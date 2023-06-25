package com.example.whatsapp_application.activities;

import android.app.Application;
import android.content.Context;

import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.repositories.MessageRepository.ChatRepository;
import com.example.whatsapp_application.repositories.MessageRepository.MessageRepository;

public class MyApplication extends Application {
    public static Context context;
    public static String token;
    public static User user;
    private static ChatRepository chatRepository;

    public static MessageRepository getMessageRepository() {
        return messageRepository;
    }

    private static MessageRepository messageRepository;
    private static String FireBaseToken;
    private static String chatId;

    public static ChatRepository getChatRepository() {
        return chatRepository;
    }

    public static void setChatRepository(ChatRepository chatRepository) {
        MyApplication.chatRepository = chatRepository;
    }

    public static String getToken() {
                return token;
        }

        public static void setUser(User user) {
                MyApplication.user = user;
        }

        public static User getUser() {
                return user;
        }



        public static String getChatId() {
                return chatId;
        }

        public static void setChatId(String chatId) {
                MyApplication.chatId = chatId;
        }



        public static void setContext(Context context) {
                MyApplication.context = context;
        }


        public static String getFireBaseToken() {
                return FireBaseToken;
        }


        public static Context getContext() {
            return context;
        }

        public static void setFireBaseToken(String newToken) {
                FireBaseToken = newToken;
        }

    public static void setMessageRepository(MessageRepository messageRepository) {

    }

    @Override
        public void onCreate() {
                super.onCreate();
                context = getApplicationContext();
                token = null;
                // create a user object
                user = null;
        }
        public static void setToken(String token) {
                MyApplication.token = token;
        }
        public static String getUserName() {
                return user.getUsername();
        }



}
