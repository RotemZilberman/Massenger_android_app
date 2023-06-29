package com.example.whatsapp_application.repositories.MessageRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.api.ChatApi;
import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.ChatRequest;
import com.example.whatsapp_application.room.ChatDao;
import com.example.whatsapp_application.room.ChatDatabase;

import java.util.List;

public class ChatRepository {
    private ChatDao chatDao;

    private ChatApi chatApi;

    public ChatRepository(Context context) {
        ChatDatabase db = ChatDatabase.getInstance(context);
        this.chatDao = db.chatDao();
        this.chatApi = new ChatApi(this.chatDao);
    }

    public void getAllChats(String token, MutableLiveData<List<Chat>> ChatListData) {
        ChatListData.postValue(chatDao.getAllChats());
        chatApi.getAllChats(token, ChatListData);
    }

    public void createChat(String username, String token, MutableLiveData<List<Chat>> chatData, MutableLiveData<Integer> success) {
        ChatRequest chatRequest = new ChatRequest(username);
        chatApi.createChat(chatRequest, token, chatData, success);
    }
    public void getChat(String chatId, String token) {
        chatApi.getChat(chatId, token);
    }

    public void deleteChat(String chatId, String token) {
        chatApi.deleteChat(chatId, token);
    }

    public void ClearChats() {
        chatDao.clear();
    }

}
