package com.example.whatsapp_application.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.DetailedUser;
import com.example.whatsapp_application.repositories.MessageRepository.UserRepository;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {
    UserRepository userRepository;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private ActivityResultLauncher<String> cameraPermissionLauncher;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityResultLauncher<Intent> cameraLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        userRepository = new UserRepository();
        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        EditText verPasswordEt = findViewById(R.id.passConfEt);
        EditText displaynameEt = findViewById(R.id.displeynameEt);

        CircleImageView pictureView = findViewById(R.id.profilePic);

        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermission();
            }
        });

        cameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            openCamera();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle extras = result.getData().getExtras();
                            if (extras != null) {
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                pictureView.setImageBitmap(imageBitmap);
                            }
                        }
                    }
                });

        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(view -> {
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            String verPassword = verPasswordEt.getText().toString();
            String displayname = displaynameEt.getText().toString();
            Drawable drawable = pictureView.getDrawable();
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();

            // Convert Bitmap to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            // Encode byte array to base64 string
            String base64String = "data:image/png;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);

            if (!username.isEmpty() && !password.isEmpty() &&
                    !verPassword.isEmpty() && !displayname.isEmpty() && !base64String.isEmpty()) {
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
                VerifySignup(username, password, verPassword, displayname, base64String, created);
            }
            else {
                Toast.makeText(getApplicationContext(), "Missing requirements. Try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void VerifySignup(String username, String password, String verPassword, String displayname,
                      String picture, MutableLiveData<Integer> created) {
        if(password.equals(verPassword)) {  //  check password verification
            if(!(password.length() < 8 || password.length() >24)) {
                if(CheckRequired(password)) {

                    userRepository.createUser(new DetailedUser(username, password, displayname, picture), created); //  request creation
                } else {
                    Toast.makeText(getApplicationContext(), "Password must include a lower and upper case letter, " +
                            "a number and a special character (only !,@,$,#,%)", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Password must be 8-24 characters", Toast.LENGTH_SHORT).show();
            }
        } else {    //  cancel creation
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }
    }

    boolean CheckRequired(String password) {
        boolean special = false;
        boolean digit = false;
        boolean lower = false;
        boolean upper = false;
        char c;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (c == '!' || c == '@' || c == '$' || c == '#'|| c == '%') {
                special = true;
            } else if(Character.isDigit(c)) {
                digit = true;
            } else if (Character.isLowerCase(c)) {
                lower = true;
            } else if (Character.isUpperCase(c)) {
                upper = true;
            } else {
                return false;
            }
        }
        return special && digit && lower && upper;
    }


    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(cameraIntent);
    }
}


