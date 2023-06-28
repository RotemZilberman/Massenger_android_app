package com.example.whatsapp_application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.viewmodels.ContactsViewModel;

public class AddContactActivity extends AppCompatActivity {

    ContactsViewModel contactsViewModel;
    private EditText usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        usernameTextView = findViewById(R.id.usernameAdd);
        Button confirmButton = findViewById(R.id.add_button);
        Button cancleButton = findViewById(R.id.cancel_button);
        MutableLiveData<Integer> success = new MutableLiveData<>();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTextView.getText().toString().trim();
                if (!username.isEmpty()) {
                    contactsViewModel.createChat(username, MyApplication.getToken(), success);
                }

            }
        });
        success.observe(this, integer -> {
            if (integer == 1) {
                finish();
            } else {
                Toast.makeText(AddContactActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
