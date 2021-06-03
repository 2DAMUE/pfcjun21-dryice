package com.sap.dryice.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sap.dryice.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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