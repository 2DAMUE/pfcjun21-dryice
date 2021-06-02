package com.sap.dryice.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sap.dryice.R;

public class LoginActivity extends AppCompatActivity {

    public static final String USERUID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginToRegisterSidebarClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    public void loginToGraphicActivity(View view) {
        startActivity(new Intent(this, GraphicsActivity.class));
        overridePendingTransition(R.anim.slide_in_top, R.anim.stay);
    }
}