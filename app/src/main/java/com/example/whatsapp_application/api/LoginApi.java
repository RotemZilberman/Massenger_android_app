package com.example.whatsapp_application.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.entities.LoginDetail;
import com.example.whatsapp_application.entities.TokenRequest;
import com.example.whatsapp_application.room.LoginDetailsDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginApi {
    Retrofit retrofit;
    WebServiceApi webServiceAPI;

    private LoginDetailsDao loginDao;

    public LoginApi(LoginDetailsDao loginDao) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceApi.class);
        this.loginDao = loginDao;
    }


    public void createToken(TokenRequest tokenRequest, MutableLiveData<String> token) {
        Call<String> call = webServiceAPI.createToken(tokenRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() != 200){
                    token.setValue(null);
                }
                else {
                    token.setValue(response.body());
                    new Thread(() -> {
                        loginDao.clear();
                        loginDao.insert(new LoginDetail(tokenRequest.getUsername(), tokenRequest.getPassword()));
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                token.setValue(null);
            }
        });

    }

}
