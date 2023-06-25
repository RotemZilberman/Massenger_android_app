package com.example.whatsapp_application.api;

import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.ChatRequest;
import com.example.whatsapp_application.entities.CompressChat;
import com.example.whatsapp_application.entities.DetailedChat;
import com.example.whatsapp_application.entities.DetailedUser;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.NewMessage;
import com.example.whatsapp_application.entities.TokenRequest;
import com.example.whatsapp_application.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceApi {
    @GET("api/Chats")
    Call<List<Chat>> getAllChats(@Header("Authorization") String token);

    @POST("api/Chats")
    Call<CompressChat> createChat(@Body ChatRequest chatRequest, @Header("Authorization") String token);

    @GET("api/Chats/{id}")
    Call<DetailedChat> getChat(@Path("id") String chatId, @Header("Authorization") String token);

    @DELETE("api/Chats/{id}")
    Call<Void> deleteChat(@Path("id") String chatId, @Header("Authorization") String token);

    @POST("api/Chats/{id}/Messages")
    Call<Message> createMessage(@Path("id") String chatId, @Body NewMessage msg , @Header("Authorization") String token);

    @GET("api/Chats/{id}/Messages")
    Call<List<Message>> getAllMessages(@Path("id") String chatId, @Header("Authorization") String token);

    @POST("api/Tokens")
    Call<String> createToken(@Body TokenRequest tokenRequest);

    @GET("api/Users/{username}")
    Call<User> getUser(@Path("username") String username, @Header("Authorization") String token);

    @POST("api/Users")
    Call<Void> createUser(@Body DetailedUser user);

    @POST("api/FirebaseTokens/{username}")
    Call<Void> sendFirebaseToken(@Body String token,@Path("username") String username);
}