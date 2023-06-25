package com.example.whatsapp_application.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.adapters.ContactsAdapter;
import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.viewmodels.ContactsViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements onClickListener {
    private TextView UsernameView;
    private  ContactsAdapter adapter;
    private  ContactsViewModel contactsViewModel;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_screen);

        TextView userNameView = findViewById(R.id.userNameTv);

        userNameView.setText(MyApplication.getUser().getUsername());
        String image = MyApplication.getUser().getProfilePic();
        String base64Image = image.substring(image.indexOf(',') + 1);
        profilePic = findViewById(R.id.userProfilePic);

        if (image != null) {
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profilePic.setImageBitmap(bitmap);
        }

        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        MyApplication.setContactsViewModel(contactsViewModel);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);

        // connect the recycler view to the adapter
         adapter = new ContactsAdapter(this, this);
        // set the adapter
        lstContacts.setAdapter(adapter);
        // set the layout manager
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        // set the observer

        contactsViewModel.getChats("Bearer " + MyApplication.getToken()).observe(this, chats -> {
            // update the cached copy of the words in the adapter.
            adapter.setChats(chats);
        });

        ImageButton addContactButton = findViewById(R.id.addContactBtn);

        addContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(ContactsActivity.this, AddContactActivity.class);
            startActivity(intent);
        });

        ImageButton settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Fetch the chats again to get updated data
        contactsViewModel.getChats(MyApplication.getToken()).observe(this, chats -> {
            adapter.setChats(chats);
        });
    }
    @Override
    public void onContactClick(Chat chat) {
        Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
        MyApplication.setChatId(chat.getId());
        // Pass the necessary data to the ChatActivity
        intent.putExtra("chatId", chat.getId());
        intent.putExtra("displayname", chat.getUser().getDisplayName());
        MyApplication.setBase64Image(chat.getUser().getProfilePic());
        // Add more data if needed
        startActivity(intent);

    }
}
