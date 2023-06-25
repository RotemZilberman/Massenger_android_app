package com.example.whatsapp_application.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.whatsapp_application.activities.MyApplication;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseService extends FirebaseMessagingService {
    public FireBaseService() {

    }

//    @Override
//    public void onMessageRecieved(@NonNull RemoteMessage message) {
//        super.onMessageReceived(message);
//        getFi;
//
//    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        MyApplication.setFireBaseToken(s);
    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        String chatID = message.getData().get("chatID");

        //TODO : chech if current user is in the chat


        // TODO : else load all chats

        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(message.getNotification().getTitle())
                .setContentText(message.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            String description = "Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


    }
}