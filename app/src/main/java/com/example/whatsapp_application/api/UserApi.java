package com.example.whatsapp_application.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp_application.entities.DetailedUser;
import com.example.whatsapp_application.entities.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    Retrofit retrofit;
    WebServiceApi webServiceAPI;

    public UserApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceApi.class);
    }

    public void getUser(String username, String token, MutableLiveData<User> user) {
        Call<User> call = webServiceAPI.getUser(username, token);
        call.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.isSuccessful() && response.body() != null){
                    user.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user.postValue(null);
            }
        });
    }

    public void createUser(DetailedUser user, MutableLiveData<Integer> status) {
        Call<Void> call = webServiceAPI.createUser(user);
        call.enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if(response.isSuccessful()){
                    status.postValue(1);
                }
                else{
                    status.postValue(0);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                status.postValue(0);
            }
        });
    }
}