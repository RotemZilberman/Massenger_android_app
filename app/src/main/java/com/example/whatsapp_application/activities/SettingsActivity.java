package com.example.whatsapp_application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.ServerAddress;
import com.example.whatsapp_application.repositories.MessageRepository.ChatRepository;
import com.example.whatsapp_application.repositories.MessageRepository.UserRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);

        EditText addressEt = findViewById(R.id.addressEt);
        SwitchMaterial modeSwitch = findViewById(R.id.modeSwitch);
        Button submitBtn = findViewById(R.id.submitBtn);
        FloatingActionButton disconnectBth = findViewById(R.id.disconnectBtn);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) { // dark mode
            modeSwitch.setActivated(false);
        } else {    //  light mode
            modeSwitch.setActivated(true);
        }

        addressEt.setText(ServerAddress.getAddress());

        disconnectBth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatRepository chats = new ChatRepository(getApplicationContext());
                chats.ClearChats();
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finishAffinity();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modeSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                if(!ServerAddress.getAddress().equals(addressEt.getText().toString())) { // changed address
                    ServerAddress.setAddress(addressEt.getText().toString());
                    ChatRepository chats = new ChatRepository(getApplicationContext());
                    chats.ClearChats();
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finishAffinity();
                }
                finish();
            }
        });
    }
}