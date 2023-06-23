package com.example.whatsapp_application.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
        // get the token from the activity that started this activity
//        String token = getIntent().getStringExtra("token");
//        String username = getIntent().getStringExtra("username");
//        String displayName = getIntent().getStringExtra("displayname");
//
//        String image = getIntent().getStringExtra("picture");
//        UsernameView = findViewById(R.id.username);
//        displayNameView = findViewById(R.id.displayName);
//        if (username == null || displayName == null || token == null) {
//            Toast.makeText(getApplicationContext(), "Error loading user", Toast.LENGTH_SHORT).show();
//            return;
//        }

        Toast.makeText(getApplicationContext(), "Password must be 8-24 characters", Toast.LENGTH_SHORT).show();

        TextView userNameView = findViewById(R.id.userNameTv);
        userNameView.setText(MyApplication.getUserName());

        // set the profile pic
//
//        if (false && image != null) {
//            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            profilePic.setImageBitmap(bitmap);
//        }
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

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
        // Pass the necessary data to the ChatActivity
        intent.putExtra("chatId", chat.getId());
        intent.putExtra("displayname", chat.getUser().getDisplayName());
        // Add more data if needed

        startActivity(intent);
    }
}
