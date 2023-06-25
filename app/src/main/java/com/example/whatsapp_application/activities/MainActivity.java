package com.example.whatsapp_application.activities;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.api.FirebaseApi;

import com.example.whatsapp_application.entities.LoginDetail;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.repositories.MessageRepository.LoginRepository;
import com.example.whatsapp_application.repositories.MessageRepository.UserRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Intent details;
    private MutableLiveData<User> result;
    private UserRepository userRepository;
    private LoginRepository loginRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /************************************************/

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String newToken) {
               // set token
                MyApplication.setFireBaseToken(newToken);
//                Log.d("token", newToken);
            }
        });

        /************************************************/
        setContentView(R.layout.login_screen);
        MutableLiveData<String> token = new MutableLiveData<>();
        details = new Intent(MyApplication.getContext(), ContactsActivity.class);
        userRepository = new UserRepository();
        loginRepository = new LoginRepository(getApplicationContext());

        result = new MutableLiveData<>(); //  user object to listen to

        result.observe(this, new Observer<User>() { //  handle when updated (found)
            @Override
            public void onChanged(User newValue) {

                if (newValue != null) { //  user exists
                    MyApplication.setUser(newValue);
                    startActivity(details);
                    finishAffinity();
                } else {    //  user does not exist
                    Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        token.observe(this, new Observer<String>() { //  if there is a remembered token
            @Override
            public void onChanged(String newValue) {
                if (newValue != null) { //  user exists
                    MyApplication.setToken(newValue);
                    MutableLiveData<LoginDetail> details = new MutableLiveData<>();
                    details.observe(MainActivity.this, new Observer<LoginDetail>() {   // when getting details of the user
                        @Override
                        public void onChanged(LoginDetail loginDetail) {
                            userRepository.getUser(loginDetail.getUsername(), "Bearer " + token.getValue(), result);
                        }
                    });

                    loginRepository.getLoginInfo(details);
                }
            }
        });

        loginRepository.LocalCreateToken(token);

        Button signBtn = findViewById(R.id.signupBtn);
        signBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });


        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(view -> { // on an connection attempt
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            if (!username.isEmpty() && !password.isEmpty()) {
                MutableLiveData<User> result = new MutableLiveData<>(); //  user object to listen to

                result.observe(this, new Observer<User>() { //  handle when updated (found)
                    @Override
                    public void onChanged(User newValue) {

                        if (newValue != null) { //  user exists
                            MyApplication.setUser(newValue);
                            FirebaseApi firebaseApi = new FirebaseApi();
                            firebaseApi.sendFirebaseToken(MyApplication.getFireBaseToken(),MyApplication.getUser().getUsername() );
                            startActivity(details);
                            finishAffinity();
                        } else {    //  user does not exist
                            Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                VerifyLogin(username, password, result);    //  check information
            }
            else {  //  not all fields were filled
                Toast.makeText(getApplicationContext(), "Missing requirements. Try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void VerifyLogin(String username, String password, MutableLiveData<User> user) {
        if(!username.isEmpty() && !password.isEmpty()) {
            MutableLiveData<String> token = new MutableLiveData<>();    //  will receive the token from database

            token.observe(this, new Observer<String>() {    //  handle return of token
                @Override
                public void onChanged(String newValue) {
                    if (newValue != null) { //  user exists

                        details.putExtra("token", newValue);
                        // set token in MyApplication
                        MyApplication.setToken("Bearer " + newValue);
                        userRepository.getUser(username, "Bearer " + newValue, user); // update user with token
                        // get the user
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            loginRepository.createToken(username, password, token); //  request token
        }
    }
}