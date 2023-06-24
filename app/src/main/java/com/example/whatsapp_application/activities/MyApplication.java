package com.example.whatsapp_application.activities;

import android.app.Application;
import android.content.Context;

import com.example.whatsapp_application.entities.User;

public class MyApplication extends Application {

        public static String getToken() {
                return token;
        }

        public static void setUser(User user) {
                MyApplication.user = user;
        }

        public static User getUser() {
                return user;
        }

        public static Context context;

        public static void setContext(Context context) {
                MyApplication.context = context;
        }

        public static String token;
        public static User user;

        public static Context getContext() {
            return context;
        }

        @Override
        public void onCreate() {
                super.onCreate();
                context = getApplicationContext();
                token = null;
                // create a user object
                user = null;
        }
        public static void setToken(String token) {
                MyApplication.token = token;
        }
        public static String getUserName() {
                return user.getUsername();
        }

        private static String Base64Image;

        public static String getBase64Image() {
                return Base64Image;
        }

        public static void setBase64Image(String base64Image) {
                Base64Image = base64Image;
        }


}
