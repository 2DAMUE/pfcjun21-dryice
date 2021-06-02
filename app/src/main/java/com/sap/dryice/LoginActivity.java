package com.sap.dryice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

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
        startActivity(new Intent(this, ActivityGraphics.class));
        overridePendingTransition(R.anim.slide_in_top, R.anim.stay);
    }
}