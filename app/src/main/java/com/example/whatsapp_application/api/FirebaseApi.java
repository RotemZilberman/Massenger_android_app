package com.example.whatsapp_application.api;

import com.example.whatsapp_application.room.LoginDetailsDao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FirebaseApi {

    Retrofit retrofit;
    WebServiceApi webServiceAPI;

    public FirebaseApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceApi.class);
    }

    // send  the firebase token to the database for the user to the server
    public void sendFirebaseToken(String token, String username) {
        webServiceAPI.sendFirebaseToken(token, username).enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {
                if(response.isSuccessful()){
                    System.out.println("Firebase token sent successfully");
                }
                else{
                    System.out.println("Firebase token not sent");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                System.out.println("Firebase token not sent");
            }
        });
    }





}
