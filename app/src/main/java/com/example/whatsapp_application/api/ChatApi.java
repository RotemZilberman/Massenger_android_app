package com.example.whatsapp_application.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.ChatRequest;
import com.example.whatsapp_application.entities.CompressChat;
import com.example.whatsapp_application.entities.DetailedChat;
import com.example.whatsapp_application.entities.ServerAddress;
import com.example.whatsapp_application.room.ChatDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApi {
    Retrofit retrofit;
    WebServiceApi webServiceAPI;

    private ChatDao chatDao;

    public ChatApi(ChatDao chatDao) {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerAddress.getAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceApi.class);
        this.chatDao = chatDao;
    }

    public void getAllChats(String token, MutableLiveData<List<Chat>> ChatListData) {
        Call<List<Chat>> call = webServiceAPI.getAllChats(token);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful()) {
                    new Thread(() -> {
                        chatDao.clear();
                        List<Chat> chats = response.body();
                        if (chats != null)
                            chatDao.insertAll(chats.toArray(new Chat[0]));
                        ChatListData.postValue(chats);
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
    private List<Chat> updateChatData(List<Chat> currentData, Chat newChat) {
        List<Chat> updatedData = new ArrayList<>(currentData);
        updatedData.add(newChat);
        return updatedData;
    }

    public void createChat(ChatRequest chatRequest, String token, MutableLiveData<List<Chat>> chatData, MutableLiveData<Integer> success) {
        Call<CompressChat> call = webServiceAPI.createChat(chatRequest, token);
        call.enqueue(new Callback<CompressChat>() {
            @Override
            public void onResponse(Call<CompressChat> call, Response<CompressChat> response) {
                if (response.isSuccessful()) {
                    CompressChat compressChat = response.body();
                    if (compressChat != null) {
                        Chat chat = compressChat.toChat();
                        new Thread(() -> {
                            chatDao.insert(chat);
                            if(chatData.getValue() != null)
                                chatData.postValue(updateChatData(chatData.getValue(), chat));  // add chat to chatData
                        }).start();
                    }
                    success.postValue(1);
                }
                else {
                    success.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<CompressChat> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                success.postValue(0);
            }
        });
    }

    public void getChat(String chatId, String token) {
        Call<DetailedChat> call = webServiceAPI.getChat(chatId, token);
        call.enqueue(new Callback<DetailedChat>() {
            @Override
            public void onResponse(Call<DetailedChat> call, Response<DetailedChat> response) {
                if (response.isSuccessful()) {
                    DetailedChat detailedChat = response.body();
                    if (detailedChat != null) {
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailedChat> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    public void deleteChat(String chatId, String token) {
        Call<Void> call = webServiceAPI.deleteChat(chatId, token);
    }

}

