package com.example.whatsapp_application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.DetailedUser;
import com.example.whatsapp_application.repositories.MessageRepository.UserRepository;

public class SignUpActivity extends AppCompatActivity {
    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        userRepository = new UserRepository();
        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        EditText verPasswordEt = findViewById(R.id.passConfEt);
        EditText displaynameEt = findViewById(R.id.displeynameEt);
        Button pictureBtn = findViewById(R.id.pictureBtn);

        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(view -> {
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            String verPassword = verPasswordEt.getText().toString();
            String displayname = displaynameEt.getText().toString();
            boolean result = false;
            if (!username.isEmpty() && !password.isEmpty() &&
                    !verPassword.isEmpty() && !displayname.isEmpty()) { //&& !picture.isEmpty()
                MutableLiveData<Integer> created = new MutableLiveData<>();

                created.observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer newValue) {
                        if (newValue == 1) {
                            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent1);
                            finishAffinity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                VerifySignup(username, password, verPassword, displayname, "pic", created);
            }
            else {
                Toast.makeText(getApplicationContext(), "Missing requirements. Try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void VerifySignup(String username, String password, String verPassword, String displayname,
                         String picture, MutableLiveData<Integer> created) {
        if(password.equals(verPassword)) {
            userRepository.createUser(new DetailedUser(username, password, displayname, picture), created);
        } else {
            created.postValue(0);
        }
    }
}

//registerBtn.setOnClickListener(view -> {
//            String username = usernameEt.getText().toString();
//            String password = passwordEt.getText().toString();
//            if (!Objects.equals(username, "") && !Objects.equals(password, "")) {
//                MutableLiveData<Integer> result = new MutableLiveData<>();
//                result.setValue(0);
//
//                result.observe(this, new Observer<Integer>() {
//                    @Override
//                    public void onChanged(Integer newValue) {
//                        if (newValue == 1) {
////                            Intent intent1 = new Intent(this, ContactsActivity.class);
////                            startActivity(intent1);
////                            finishAffinity();
//                        } else if (newValue == 0) {
//                            Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                verifier.VerifyLogin(username, password, result);
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "Missing requirements. Try again!", Toast.LENGTH_SHORT).show();
//            }
//        });