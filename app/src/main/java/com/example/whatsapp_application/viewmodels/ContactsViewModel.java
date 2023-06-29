package com.example.whatsapp_application.viewmodels;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp_application.activities.MyApplication;
import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.repositories.MessageRepository.ChatRepository;

import java.util.List;


public class ContactsViewModel extends ViewModel {

    MutableLiveData<List<Chat>> chats;
    private final ChatRepository chatRepository;


    public ContactsViewModel() {
        this.chatRepository = new ChatRepository(
            MyApplication.getContext()
        );

        chats = new MutableLiveData<>();

    }
public LiveData<List<Chat>> getChats(String token) {
        // update the chats in the repository
        chatRepository.getAllChats(token, chats);
        return chats;
}

public void createChat(String username, String token, MutableLiveData<Integer> success) {
        chatRepository.createChat(username, token, chats, success);
}

    public void deleteChat(String chatId, String token) {
        chatRepository.deleteChat(chatId, token);
}
    public void ClearChats() {
        chatRepository.ClearChats();
}
}

