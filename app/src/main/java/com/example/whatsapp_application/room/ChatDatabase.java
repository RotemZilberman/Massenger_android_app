package com.example.whatsapp_application.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.LoginDetail;
import com.example.whatsapp_application.entities.Message;

@Database(entities = {Chat.class, Message.class, LoginDetail.class}, version = 4)
@TypeConverters({Converters.class})
    public abstract class ChatDatabase extends RoomDatabase {
    public abstract ChatDao chatDao();
    public abstract MessageDao messageDao();

    public abstract LoginDetailsDao loginDetailsDao();
    private static ChatDatabase instance;

    public static ChatDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                            ChatDatabase.class, "chat_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

