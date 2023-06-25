package com.example.whatsapp_application.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp_application.activities.MyApplication;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.repositories.MessageRepository.MessageRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {

    MutableLiveData<List<Message>> messages;
    private final MessageRepository messageRepository;

    public MessageViewModel() {
        this.messageRepository = new MessageRepository(MyApplication.getContext());
        MyApplication.setMessageRepository(messageRepository);
        messages = new MutableLiveData<>();
    }
    public LiveData<List<Message>> createMessage(String chatId, String msg, String token) {
        messageRepository.createMessage(chatId, msg, token, messages);
        return messages;
    }
    public void ClearMessages() {
        messageRepository.ClearMessages();
    }
    public MessageRepository getMessageRepository() {
        return messageRepository;
    }
    public LiveData<List<Message>> getMessages(String chatId,String token) {
        messageRepository.getAllMessages(chatId,token, messages);
        return messages;
    }

}
