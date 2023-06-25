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
    RecyclerView recyclerView;
    private MessageViewModel messageViewModel;
    private MessagesAdapter adapter;
    private ImageView senderImageView;

    private TextView displayNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        String chatId = getIntent().getStringExtra("chatId");
        String token = MyApplication.getToken();

        String displayName = getIntent().getStringExtra("displayname");

        String image = MyApplication.getBase64Image();

        displayNameView = findViewById(R.id.displayNameTv);
        displayNameView.setText(displayName);
        // set the sender's profile picture
        senderImageView = findViewById(R.id.pictureIv);
        if ( image != null && !image.equals("pic")) {
            image = image.substring(image.indexOf(',') + 1);
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            senderImageView.setImageBitmap(bitmap);
        }
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        MyApplication.setChatId(chatId);
        MyApplication.setMessageViewModel(messageViewModel);

        // set adapter for the recycler view
        recyclerView = findViewById(R.id.lstMessages);
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
            adapter.setMessages(messages);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        messageViewModel.getMessages(MyApplication.getChatId(), MyApplication.getToken()).observe(this, messages -> {
            if (messages == null) {
                Toast.makeText(getApplicationContext(), "Error loading messages", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter.setMessages(messages);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            MyApplication.setChatId(getIntent().getStringExtra("chatId"));
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.setChatId(null);
        finish();
    }

}





