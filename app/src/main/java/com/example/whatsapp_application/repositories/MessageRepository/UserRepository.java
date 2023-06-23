package com.example.whatsapp_application.repositories.MessageRepository;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.api.UserApi;
import com.example.whatsapp_application.entities.DetailedUser;
import com.example.whatsapp_application.entities.User;

public class UserRepository {
    private UserApi userApi;

    public UserRepository() {
        this.userApi = new UserApi();
    }

    public void getUser(String username, String token, MutableLiveData<User> user) {
        userApi.getUser(username, token, user);
    }

    public void createUser(DetailedUser user, MutableLiveData<Integer> status) {
        userApi.createUser(user, status);
    }
}