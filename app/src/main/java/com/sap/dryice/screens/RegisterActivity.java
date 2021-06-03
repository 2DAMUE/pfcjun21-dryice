package com.sap.dryice.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.sap.dryice.R;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout name;
    private TextInputLayout email;
    private TextInputLayout passwd;
    private TextInputLayout confirmPasswd;
    private Button btnRegister;

    private static final int REQUEST_PERMISSON_CODE = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;
    private Uri imageUri;
    private ImageView galleryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.textInputNombreRegister);
        email = findViewById(R.id.textInputEmailRegister);
        passwd = findViewById(R.id.textInputPassRegister);
        confirmPasswd = findViewById(R.id.textInputRePassRegister);
        btnRegister = findViewById(R.id.continueRegisterBtn);
        galleryImage = (ImageView) findViewById(R.id.imgProfile);

        selectAndChangeProfileImage();
        continueRegistration();


    }

    private void selectAndChangeProfileImage() {
        galleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        openGallery();
                    } else {
                        ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSON_CODE);
                    }
                } else {

                }

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_GALLERY) {
            imageUri = data.getData();



            Glide.with(this)
                    .load(imageUri)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(300))
                    .circleCrop()
                    .into(galleryImage);
        } else {
            Log.i("RAG", "Result: " + resultCode);
            Toast.makeText(this, "You did not choose any photo", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSON_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "You need to enable permissions", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void continueRegistration() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String correo = email.getEditText().getText().toString().trim();
        String usuario = name.getEditText().getText().toString().trim();
        String pwd = passwd.getEditText().getText().toString().trim();
        String confirmPwd = confirmPasswd.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(correo)){
            email.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            passwd.setError("Enter your password");
            return;
        } else if (pwd.length() < 6) {
            passwd.setError("Minimum length of password should be 6");
            return;
        } else if (TextUtils.isEmpty(usuario)) {
            name.setError("Enter your username");
            return;
        } else if (TextUtils.isEmpty(confirmPwd)) {
            confirmPasswd.setError("Confirm your password");
            return;
        } else if (!pwd.equals(confirmPwd)) {
            confirmPasswd.setError("Passwords are different");
            return;
        } else if (!isValidEmail(correo)) {
            email.setError("This is not a valid email");
            return;
        } else {
            Toast.makeText(getApplicationContext(), "Email and Password added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), RaspberryRegisterActivity.class);
            intent.putExtra("Name", usuario);
            intent.putExtra("Email", correo);
            intent.putExtra("Password", pwd);
            intent.putExtra("UriImg", imageUri);
            startActivity(intent);
        }
    }

    private boolean isValidEmail(String correo) {
        return (!TextUtils.isEmpty(correo) && Patterns.EMAIL_ADDRESS.matcher(correo).matches());
    }

    public void registerToLoginSidebarClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
    public void registerToRaspberryConfigClick(View view) {
        startActivity(new Intent(this, RaspberryRegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}