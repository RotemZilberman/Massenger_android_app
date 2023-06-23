package com.example.whatsapp_application.repositories.MessageRepository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.api.LoginApi;
import com.example.whatsapp_application.entities.LoginDetail;
import com.example.whatsapp_application.entities.TokenRequest;
import com.example.whatsapp_application.room.ChatDatabase;
import com.example.whatsapp_application.room.LoginDetailsDao;

public class LoginRepository {

    private LoginApi loginApi;

    private LoginDetailsDao loginDetailsDao;

    public LoginRepository(Context context) {
        ChatDatabase db = ChatDatabase.getInstance(context);
        this.loginDetailsDao = db.loginDetailsDao();
        this.loginApi = new LoginApi(this.loginDetailsDao);
    }
    public void createToken(String username, String password, MutableLiveData<String> token) {
        TokenRequest tokenRequest = new TokenRequest(username, password);
        loginApi.createToken(tokenRequest, token);
    }

    public void LocalCreateToken(MutableLiveData<String> token) {
        new Thread(() -> {
            LoginDetail loginDetail = loginDetailsDao.getLoginDetails();
            if (loginDetail != null) {
                TokenRequest tokenRequest = new TokenRequest(loginDetail.getUsername(), loginDetail.getPassword());
                loginApi.createToken(tokenRequest, token);
            }
        }).start();
    }

    public void ClearLoginDetails() {
        loginDetailsDao.clear();
    }
}