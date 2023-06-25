package com.example.whatsapp_application.activities;

import android.app.Application;
import android.content.Context;

import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.repositories.MessageRepository.ChatRepository;
import com.example.whatsapp_application.repositories.MessageRepository.MessageRepository;
import com.example.whatsapp_application.viewmodels.ContactsViewModel;
import com.example.whatsapp_application.viewmodels.MessageViewModel;

public class MyApplication extends Application {
    public static Context context;
    public static String token;
    public static User user;
    private static Chat chat;
    private static String FireBaseToken;
    private static String chatId;
    private static ContactsViewModel contactsViewModel;

    public static MessageViewModel getMessageViewModel() {
        return messageViewModel;
    }

    public static void setMessageViewModel(MessageViewModel messageViewModel) {
        MyApplication.messageViewModel = messageViewModel;
    }

    private static MessageViewModel messageViewModel;

    public static ContactsViewModel getContactsViewModel() {
        return contactsViewModel;
    }

    public static void setContactsViewModel(ContactsViewModel contactsViewModel) {
        MyApplication.contactsViewModel = contactsViewModel;
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

    public static void setChat(Chat chat) {
        MyApplication.chat = chat;
    }

    public static Chat getChat() {
        return chat;
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
