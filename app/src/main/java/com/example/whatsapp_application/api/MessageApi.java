package com.example.whatsapp_application.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.NewMessage;
import com.example.whatsapp_application.entities.ServerAddress;
import com.example.whatsapp_application.room.MessageDao;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageApi {
    Retrofit retrofit;
    WebServiceApi webServiceAPI;

    private MessageDao messageDao;

    public MessageApi(MessageDao messageDao) {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerAddress.getAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceApi.class);
        this.messageDao = messageDao;
    }

    public void createMessage(String chatId, String msg, String token, MutableLiveData<List<Message>> messageData) {
        Call<Message> call = webServiceAPI.createMessage(chatId, new NewMessage(msg) , token);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful() && response.body() != null){
                    Message message = response.body();
                    message.setChatId(chatId);
                    new Thread(() -> {
                        messageDao.insert(message);
                        List<Message> messages = messageDao.getAllMessages(chatId);
                        messageData.postValue(messages);
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getAllMessages(String chatId, String token, MutableLiveData<List<Message>> messageData) {
        Call<List<Message>> call = webServiceAPI.getAllMessages(chatId, token);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                new Thread(() -> {
                    messageDao.clear(chatId);
                    List<Message> messages = response.body();

                    if (messages != null) {
                        Collections.reverse(messages);
                        for (Message message : messages)
                            message.setChatId(chatId);
                        messageDao.insertAll(messages.toArray(new Message[0]));
                        messageData.postValue(messages);
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
