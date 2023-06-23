package com.example.whatsapp_application.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whatsapp_application.entities.LoginDetail;

@Dao
public interface LoginDetailsDao {
    @Insert
    void insert(LoginDetail loginDetail);

    @Query("SELECT * FROM loginDetails")
    LoginDetail getLoginDetails();

    @Query("DELETE FROM loginDetails")
    void clear();

}
