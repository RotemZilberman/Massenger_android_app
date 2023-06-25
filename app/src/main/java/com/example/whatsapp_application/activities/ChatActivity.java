package com.example.whatsapp_application.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.adapters.MessagesAdapter;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.viewmodels.MessageViewModel;

public class ChatActivity extends AppCompatActivity {
    private MessagesAdapter adapter;
    private ImageView senderImageView;

    private TextView displayNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        String chatId = getIntent().getStringExtra("chatId");
        String token = MyApplication.getToken();

        String displayName = getIntent().getStringExtra("displayName");

        String image = getIntent().getStringExtra("image");

        displayNameView = findViewById(R.id.displayNameTv);
        displayNameView.setText(displayName);
        // set the sender's profile picture
        senderImageView = findViewById(R.id.pictureIv);
        if (false && image != null) {
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            senderImageView.setImageBitmap(bitmap);
        }
        MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        MyApplication.setChatId(chatId);
        MyApplication.setMessageViewModel(messageViewModel);

        // set adapter for the recycler view
        RecyclerView recyclerView = findViewById(R.id.lstMessages);
        // create the adapter
        adapter = new MessagesAdapter(this);
        // set the adapter
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the messageEditText and sendButton
        EditText messageEditText = findViewById(R.id.messageEditText);
        ImageButton sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String messageText = messageEditText.getText().toString().trim();
            messageViewModel.createMessage(chatId, messageText, token).observe(this, messages -> {
                        if (messages == null) {
                            Toast.makeText(getApplicationContext(), "Error sending message", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter.setMessages(messages);

                        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                        // clean the text
                        messageEditText.setText("");
                    }
            );

        });

        // add contact button listen

        // Existing code...
        messageViewModel.getMessages(chatId, token).observe(this, messages -> {
            if (messages == null) {
                Toast.makeText(getApplicationContext(), "Error loading messages", Toast.LENGTH_SHORT).show();
                return;
            }
//            Collections.reverse(messages); // Reverse the order of messages
            adapter.setMessages(messages);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        });
    }
}





