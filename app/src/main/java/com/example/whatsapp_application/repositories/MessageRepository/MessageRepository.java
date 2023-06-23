package com.example.whatsapp_application.repositories.MessageRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.api.MessageApi;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.room.ChatDatabase;
import com.example.whatsapp_application.room.MessageDao;

import java.util.List;

public class MessageRepository {

    private MessageApi messageApi;

    private MessageDao messageDao;

    public MessageRepository(Context context) {
        ChatDatabase db = ChatDatabase.getInstance(context);
        this.messageDao = db.messageDao();
        this.messageApi = new MessageApi(this.messageDao);
    }

    public void createMessage(String chatId, String msg, String token, MutableLiveData<List<Message>> messageData) {
        messageApi.createMessage(chatId, msg , token, messageData);
    }

    public void getAllMessages(String chatId, String token, MutableLiveData<List<Message>> messageData) {
        messageData.setValue(messageDao.getAllMessages(chatId));
        messageApi.getAllMessages(chatId, token, messageData);
    }

    public void ClearMessages() {
        messageDao.clearALL();
    }
}
